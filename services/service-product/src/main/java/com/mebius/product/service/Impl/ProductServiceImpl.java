package com.mebius.product.service.Impl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.mebius.bean.product.Product;
import com.mebius.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("5"));
        product.setProductName("苹果-"+productId);
        product.setNum(2);
        log.info(product.toString());
        return product;
    }
}
