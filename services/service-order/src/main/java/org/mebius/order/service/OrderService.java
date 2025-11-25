package org.mebius.order.service;

import org.mebius.bean.order.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId);

    String getConfig();
}
