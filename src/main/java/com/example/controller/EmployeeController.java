package com.example.controller;

import com.example.dto.EmployeeDto;
import com.example.service.EmployeeService;
import io.micronaut.http.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController
{
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Post
    public EmployeeDto createEmployee(@Body EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Get
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Get("/{id}")
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Put("/{id}")
    public EmployeeDto updateEmployee(Long id, @Body EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    @Delete("/{id}")
    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }

}
