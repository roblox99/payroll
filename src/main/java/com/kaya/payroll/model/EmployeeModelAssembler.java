package com.kaya.payroll.model;

import com.kaya.payroll.controller.EmployeeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// Spring will automatically create an instance of this, thanks to the Component.
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    // This function converts Employee objects to EntityModel<Employee> objects for link creation in RESTful APIs.
    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(
                employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
        );
    }

}
