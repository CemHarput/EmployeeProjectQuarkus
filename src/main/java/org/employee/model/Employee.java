package org.employee.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Employee extends PanacheEntity {

    private String name;
    private String surname;
    private int age;
    private BigDecimal salary;
    private int workYears;

    private String title;


    public Employee(String name, String surname, int age, BigDecimal salary, int workYears, String title) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.workYears = workYears;
        this.title = title;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getWorkYears() {
        return workYears;
    }

    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
