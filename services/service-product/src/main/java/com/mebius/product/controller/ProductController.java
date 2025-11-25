package com.mebius.product.controller;

import com.mebius.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.mebius.bean.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId,
                              HttpServletRequest request){
        String header = request.getHeader("X-Token");
        System.out.println("header================:"+header);
        return productService.getProductById(productId);

    }
}
