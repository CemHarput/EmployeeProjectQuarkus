package org.employee.controller;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.employee.dto.EmployeeDTO;
import org.employee.dto.EmployeeRequestDTO;
import org.employee.exception.EmployeeCreationException;
import org.employee.exception.EntityNotFoundException;
import org.employee.service.EmployeeService;
import org.jboss.logging.Logger;
import jakarta.validation.Valid;

import java.util.List;

@Path("/api/v1/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Employee API", description = "Manage employee records")
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Operation(summary = "Get all employees", description = "Returns a list of employees with pagination")
    public Response getAllEmployees(@QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("size") @DefaultValue("10") int size) {
        logger.debug("getAllEmployees started");
        List<EmployeeDTO> employeeDtoList = employeeService.getAllEmployees(page, size);
        return Response.ok(employeeDtoList).build();
    }

    @POST
    @Operation(summary = "Create a new employee", description = "Adds a new employee record")
    public Response createEmployee(@Valid EmployeeRequestDTO addEmployeeRequestDTO) {
        try {
            logger.debug("createEmployee started");
            Long employeeId = employeeService.createEmployee(addEmployeeRequestDTO);
            logger.info("Employee created successfully with ID: " + employeeId);
            return Response.status(Response.Status.CREATED).entity(employeeId.toString()).build();
        } catch (EmployeeCreationException e) {
            logger.error("createEmployee failed due to: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Unexpected error during employee creation: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @PUT
    @Path("/{employeeId}")
    @Operation(summary = "Update an employee", description = "Updates an existing employee by ID")
    public Response updateEmployee(@PathParam("employeeId") Long employeeId,
                                   @Valid EmployeeRequestDTO updateEmployeeRequestDTO) {
        try {
            logger.debug("updateEmployee started for employeeId: " + employeeId);
            Long updatedEmployeeId = employeeService.updateEmployee(employeeId, updateEmployeeRequestDTO);
            logger.info("Employee updated successfully with ID: " + employeeId);
            return Response.ok(updatedEmployeeId.toString()).build();
        } catch (EntityNotFoundException e) {
            logger.error("Employee with ID " + employeeId + " not found: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating employee: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @DELETE
    @Path("/{employeeId}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee by ID")
    public Response deleteEmployee(@PathParam("employeeId") Long employeeId) {
        try {
            logger.debug("deleteEmployee started for employeeId: " + employeeId);
            employeeService.deleteEmployee(employeeId);
            logger.info("Employee deleted successfully with ID: " + employeeId);
            return Response.ok(employeeId.toString()).build();
        } catch (EntityNotFoundException e) {
            logger.error("Employee with ID " + employeeId + " not found: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting employee: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }
}
