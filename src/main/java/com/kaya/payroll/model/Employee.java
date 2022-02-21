package com.kaya.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
public class Employee {

    // marks id as the primary key and that it should be automatically generated
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String role;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setName(String name) {
        String[] nameParts = name.split(" ");
        firstName = nameParts[0];
        lastName = nameParts[1];
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
        return id.equals(employee.id) && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, firstName=\"%s\", lastName=\"%s\", role=\"%s\"}",
                id,
                firstName,
                lastName,
                role
        );
    }

}
