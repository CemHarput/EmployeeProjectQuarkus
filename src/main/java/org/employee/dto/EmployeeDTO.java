package org.employee.dto;

import org.employee.model.Employee;

import java.math.BigDecimal;

public record EmployeeDTO(Long id, String name, String surname, int age, BigDecimal salary, int workYears, String title) {

    public static EmployeeDTO fromEntity(Employee employee) {
        return new EmployeeDTO(employee.id, employee.getName(), employee.getSurname(), employee.getAge(),
                employee.getSalary(), employee.getWorkYears(), employee.getTitle());
    }
}
