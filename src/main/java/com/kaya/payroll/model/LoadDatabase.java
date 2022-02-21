package com.kaya.payroll.model;

import com.kaya.payroll.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This class is automatically loaded by Spring.
// Once the application context is loaded Spring Boot will run all CommandLineRunner beans.
// This particular runner will request a copy of the EmployeeRepository and store two new Employees.
@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            logger.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
        };
    }

}
