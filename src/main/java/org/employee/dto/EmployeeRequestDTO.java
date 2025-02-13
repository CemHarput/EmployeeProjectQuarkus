package org.employee.dto;


import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record EmployeeRequestDTO (String name, String surname, int age, double salary, int workYears, String title){
}
