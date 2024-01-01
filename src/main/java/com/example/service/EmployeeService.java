package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.entity.EmployeeEntity;
import com.example.repository.EmployeeRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class EmployeeService
{


    private final EmployeeRepository employeeRepository; // Injecting EmployeeRepository

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = toEmployeeEntity(employeeDto);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return toEmployeeDto(savedEmployee);
    }

    @Transactional
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream().map(this::toEmployeeDto).collect(Collectors.toList());
    }

    @Transactional
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(this::toEmployeeDto);
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Update existingEmployee fields with data from employeeDto
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setPhoneNumber(employeeDto.getPhoneNumber());

        EmployeeEntity updatedEmployee = employeeRepository.update(existingEmployee);
        return toEmployeeDto(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Helper methods for conversion between DTO and Entity
    private EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(employeeDto.getName());
        entity.setEmail(employeeDto.getEmail());
        entity.setPhoneNumber(employeeDto.getPhoneNumber());
        // Set other fields as needed
        return entity;
    }

    private EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setName(employeeEntity.getName());
        dto.setEmail(employeeEntity.getEmail());
        dto.setPhoneNumber(employeeEntity.getPhoneNumber());
        // Set other fields as needed
        return dto;
    }
}
