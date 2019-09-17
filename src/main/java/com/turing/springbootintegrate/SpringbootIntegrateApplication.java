package com.turing.springbootintegrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.turing.springbootintegrate"})
public class SpringbootIntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootIntegrateApplication.class, args);
    }

}
