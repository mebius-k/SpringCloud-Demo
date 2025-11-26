package org.mebius.order.feign;

import org.mebius.bean.product.Product;
import org.mebius.order.fallback.ProductFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallback = ProductFallBack.class)
public interface ProductFeign {
    @GetMapping("product/{productId}")
    Product getProduct(@PathVariable("productId") Long productId);
}
