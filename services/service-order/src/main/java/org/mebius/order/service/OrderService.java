package org.mebius.order.service;

import org.mebius.bean.order.Order;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {
    Order createOrder(Long userId, Long productId);

    String getConfig();

    Order secKill(Long userId, Long productId);
}
