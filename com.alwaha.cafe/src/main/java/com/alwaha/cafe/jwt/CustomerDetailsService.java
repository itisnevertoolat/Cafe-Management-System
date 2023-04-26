package com.alwaha.cafe.jwt;

import com.alwaha.cafe.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService  implements UserDetailsService {
    @Autowired
    UserDao userDao;

    private com.alwaha.cafe.models.User userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetail = userDao.findByEmailId(username);
        log.info("inside findByEmailId {}", username);
        if(!Objects.isNull(userDetail)){
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }else
            throw  new UsernameNotFoundException("This user isn't here");
    }
    public com.alwaha.cafe.models.User getUserDetail(){
        return userDetail;
    }
}


