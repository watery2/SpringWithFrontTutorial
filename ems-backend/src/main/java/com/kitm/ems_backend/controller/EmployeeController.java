package com.kitm.ems_backend.controller;

import com.kitm.ems_backend.dto.EmployeeDto;
import com.kitm.ems_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    // Build add employee Rest API

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // build get employee Rest api

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId)
    {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();

        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee)
    {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
