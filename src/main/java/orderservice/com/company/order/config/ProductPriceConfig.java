package orderservice.com.company.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProductPriceConfig {

    // product price map
    @Bean
    public Map<Integer, Integer> productPrice() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,100);
        map.put(2,200);
        map.put(3,400);
        return map;
    };

}