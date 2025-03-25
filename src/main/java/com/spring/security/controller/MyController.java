package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class MyController{

    // @GetMapping("")
    // public String WelcomeSpringSecurity(){
    //     return "Welcome to spring security 6";
    // }


    @GetMapping("/csrf")
    public CsrfToken getCSRF(HttpServletRequest request){
        return  (CsrfToken) request.getAttribute("_csrf");
    }

    private record Product(Integer id, String productName, Double productPrice){};


    List<Product> products = new ArrayList<>(
        List.of(
            new Product(1, "Iphone", 22.0),
            new Product(2, "Ipad", 23.0)
        )
    );

    @PostMapping
    public Product saveProdut(@RequestBody Product product){
        products.add(product);
        return  product;
    }


    @GetMapping
    public List<Product> getProducts(){
        return products;
    }
    

}