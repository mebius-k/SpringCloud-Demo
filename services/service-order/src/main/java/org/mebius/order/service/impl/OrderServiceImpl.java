package org.mebius.order.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mebius.bean.order.Order;
import org.mebius.bean.product.Product;
import org.mebius.order.config.OrderConfig;
import org.mebius.order.feign.ProductFeign;
import org.mebius.order.properties.OrderProperties;
import org.mebius.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//@RefreshScope//自动刷新
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private OrderProperties orderProperties;
    @Autowired
    private ProductFeign productFeign;

    @SentinelResource(value = "createOrder")
    @Override
    public Order createOrder(Long userId, Long productId) {
//        Product product = getProductFromRemote(productId);
        Product product = productFeign.getProduct(productId);
        BigDecimal price = product.getPrice();
        int num = product.getNum();
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(price.multiply(new BigDecimal(num)));
        order.setUserId(userId);
        order.setNickName("mebius");
        order.setAddress("beijing");
        order.setProductList(List.of(product));
        return order;
    }

   @Value("${spring.profiles.active}")
   private String activeProfile;

    @Override
    public String getConfig() {
        System.out.println("orderTimeOut: "+orderProperties.getTimeOut()+" orderAutoConfig:"+orderProperties.getAutoConfig());
        return "orderTimeOut: "+orderProperties.getTimeOut()+" orderAutoConfig:"+orderProperties.getAutoConfig()+" activeProfile:"+activeProfile;

    }

    //远程调用获取商品信息
    public Product getProductFromRemote(Long productId) {
        try {
            //1、获取到商品服务所在的所有机器IP+port
//            List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//            ServiceInstance instance = instances.get(0);
            //远程URL 负载均衡获取
//            ServiceInstance choose = loadBalancerClient.choose("service-product");
//            String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
            //基于注解的负载均衡
            String url = "http://service-product:/product/" + productId;
            log.info("远程请求：{}", url);
            //2、给远程发送请求
            return restTemplate.getForObject(url, Product.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
