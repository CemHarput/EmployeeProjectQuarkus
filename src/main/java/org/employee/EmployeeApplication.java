package org.employee;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.employee.model.Employee;

import java.math.BigDecimal;
import java.util.List;

@Startup
@ApplicationScoped
public class EmployeeApplication {

    @Transactional
    public void init() {
        System.out.println("Inserting initial employee data...");


        Employee jim = new Employee("Jim", "Halpert", 33, new BigDecimal("10000"), 5, "Sales Junior");
        Employee dwight = new Employee("Dwight", "Schrute", 32, new BigDecimal("20000"), 5, "Assistant to the Regional Manager");
        Employee michael = new Employee("Michael", "Scott", 42, new BigDecimal("10000"), 10, "Regional Manager");
        Employee stanley = new Employee("Stanley", "Hudson", 50, new BigDecimal("14000"), 10, "Sales Executive");

        PanacheEntityBase.persist(List.of(jim, dwight, michael, stanley));

        System.out.println("Employee data inserted successfully.");
    }
}
