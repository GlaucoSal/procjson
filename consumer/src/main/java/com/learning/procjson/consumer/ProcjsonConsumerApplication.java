package com.learning.procjson.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.learning.procjson.shared.model")
public class ProcjsonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcjsonConsumerApplication.class, args);
    }

}
