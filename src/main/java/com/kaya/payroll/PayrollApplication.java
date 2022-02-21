package com.kaya.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBootApplication is a meta-annotation that pulls component scanning, auto-config. and property support.
// In effect it fires up a servlet container and serves our service.
@SpringBootApplication
public class PayrollApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

}
