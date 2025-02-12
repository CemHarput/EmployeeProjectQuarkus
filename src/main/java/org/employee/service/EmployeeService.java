package org.employee.service;



import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.employee.dto.EmployeeDTO;
import org.employee.dto.EmployeeRequestDTO;
import org.employee.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class EmployeeService {

    public List<EmployeeDTO> getAllEmployees(int page, int size) {
        PanacheQuery<Employee> query = Employee.findAll();
        return query.page(page, size)
                .list()
                .stream()
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Long createEmployee(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        updateEmployeeFromDTO(dto, employee);
        employee.persist();
        return employee.id;
    }

    public Long updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee = Employee.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
        updateEmployeeFromDTO(dto, employee);
        employee.persist();
        return employee.id;
    }

    public void deleteEmployee(Long id) {
        Employee employee = Employee.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
        employee.delete();
    }

    private void updateEmployeeFromDTO(EmployeeRequestDTO dto, Employee employee) {
        employee.setName(dto.name());
        employee.setSurname(dto.surname());
        employee.setAge(dto.age());
        employee.setSalary(BigDecimal.valueOf(dto.salary()));
        employee.setWorkYears(dto.workYears());
        employee.setTitle(dto.title());
    }
}

