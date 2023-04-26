package com.alwaha.cafe.rest;

import com.alwaha.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")
public interface ProductRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addProduct(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path="/get")
    ResponseEntity<List<ProductWrapper>> getAllProducts();

    @PostMapping(path="/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path="/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    @PostMapping(path="/status")
    ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);
    @GetMapping(path="/category/{id}")
    ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);

    @GetMapping(path = "/{id}")
    ResponseEntity<ProductWrapper> getById(@PathVariable Integer id);

}
