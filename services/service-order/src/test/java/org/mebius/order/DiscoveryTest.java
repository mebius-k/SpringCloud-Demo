package org.mebius.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void test() {
        discoveryClient.getServices().forEach(System.out::println);
    }
}
