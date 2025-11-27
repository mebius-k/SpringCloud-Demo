package org.mebius.order.fallback;
import java.math.BigDecimal;

import org.mebius.bean.product.Product;
import org.mebius.order.feign.ProductFeign;
import org.springframework.stereotype.Component;

@Component
public class ProductFallBack implements ProductFeign {
    @Override
    public Product getProduct(Long productId) {
        System.out.println("openfeign 兜底回调==============");
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("兜底数据！！！");
        product.setNum(0);
        return product;
    }
}
