package com.kaya.payroll.model;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
public class Employee {

    // marks id as the primary key and that it should be automatically generated
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;

    public Employee() {

    }

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && name.equals(employee.name) && role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, name=\"%s\", role=\"%s\"}",
                id,
                name,
                role
        );
    }

}
