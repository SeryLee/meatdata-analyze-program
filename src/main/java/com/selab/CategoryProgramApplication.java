package com.selab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.selab.categoryprogram"})
public class CategoryProgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryProgramApplication.class, args);
    }
}
