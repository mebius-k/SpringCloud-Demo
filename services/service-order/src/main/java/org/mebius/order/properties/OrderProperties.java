package org.mebius.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "order")
@Component
@Data
public class OrderProperties {
    private String timeOut;
    private String autoConfig;
}
