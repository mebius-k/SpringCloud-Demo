package org.mebius.order.controller;

import org.mebius.bean.order.Order;
import org.mebius.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long userId, @RequestParam Long productId) {
        return orderService.createOrder(userId, productId);
    }

    @GetMapping("/config")
    public String getConfig() {
       return orderService.getConfig();
    }
}
