package com.kaya.payroll.controller;

import com.kaya.payroll.model.Employee;
import com.kaya.payroll.model.EmployeeNotFoundException;
import com.kaya.payroll.repository.EmployeeRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;


// RestController makes sure that the data returned by each method gets written into the response body
// instead of rendering a template.
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    // An EmployeeRepository will be injected into this constructor.
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

//    No-REST:
//    @GetMapping("/employees")
//    public List<Employee> all() {
//        return repository.findAll();
//    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll().stream().map(
                employee -> EntityModel.of(
                        employee,
                        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
                )
        ).collect(Collectors.toList());

        return CollectionModel.of(
                employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel()
        );
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

//    No-REST:
//    @GetMapping("/employees/{id}")
//    public Employee one(@PathVariable Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//        // When an exception is thrown Spring will make sure to render an HTTP 404 error page.
//    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id)
        );

        // EntityModel is a generic container for the data and links necessary for RESTful APIs.
        return EntityModel.of(
                employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),   // asks Spring HATEOAS to build a link to the one() method and flag it as self
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees")   // asks Spring HATEOAS to build a link to the all() method and call it employees
        );
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
