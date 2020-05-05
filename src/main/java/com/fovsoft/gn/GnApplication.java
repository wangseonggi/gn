package com.fovsoft.gn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class GnApplication {
    public static void main(String[] args) {
        SpringApplication.run(GnApplication.class, args);
    }
}
