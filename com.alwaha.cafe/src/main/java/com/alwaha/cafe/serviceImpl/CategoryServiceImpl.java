package com.alwaha.cafe.serviceImpl;

import com.alwaha.cafe.dao.CategoryDao;
import com.alwaha.cafe.jwt.JwtFilter;
import com.alwaha.cafe.models.Category;
import com.alwaha.cafe.service.CategoryService;
import com.alwaha.cafe.utils.CafeUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CategoryDao categoryDao;
    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                if(validateCategoryMap(requestMap, false)){
                    categoryDao.save(getCategoryFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);

                }

            }else{
                return CafeUtils.getResponseEntity("hey dude, you can't access this", HttpStatus.UNAUTHORIZED);
            }
            return CafeUtils.getResponseEntity("Something went Wrong", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories(String filterValue) {
        try{
            if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
                return new ResponseEntity<>(categoryDao.getAllCategories(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                if(validateCategoryMap(requestMap, true)){
                    Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                    if(!optional.isEmpty()){
                        categoryDao.save(getCategoryFromMap(requestMap, true));
                        return new ResponseEntity<>("Category updated Successfully", HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("Category id doesn't exist", HttpStatus.BAD_REQUEST);
                    }

                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }else {
                return new ResponseEntity<>("Sorry dude, but you can't access this", HttpStatus.UNAUTHORIZED);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, boolean isAdd) {

        Category category = new Category();
        if(isAdd){
            category.setId(Integer.parseInt(requestMap.get("id")));

        }
        category.setName(requestMap.get("name"));
        return category;
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }
}
