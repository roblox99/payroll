package com.kaya.payroll.model;

import com.kaya.payroll.repository.EmployeeRepository;
import com.kaya.payroll.repository.OrderRepository;
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
    public CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));
            employeeRepository.findAll().forEach(
                    employee -> logger.info("Preloaded " + employee)
            );

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone 13", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(
                    order -> logger.info("Preloading " + order)
            );
        };
    }

}
