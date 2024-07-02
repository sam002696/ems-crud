package com.sami.ems_backend.service.impl;

import com.sami.ems_backend.dto.EmployeeDto;
import com.sami.ems_backend.entity.Employee;
import com.sami.ems_backend.exception.ResourceNotFoundException;
import com.sami.ems_backend.mapper.EmployeeMapper;
import com.sami.ems_backend.repository.EmployeeRepository;
import com.sami.ems_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long EmployeeId) {
      Employee employee =  employeeRepository.findById(EmployeeId).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exist with the given id"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
      List<Employee> employees =   employeeRepository.findAll();
      return employees.stream().map((employee -> EmployeeMapper.mapToEmployeeDto(employee))).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exist with the given id"));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exist with the given id"));

        employeeRepository.deleteById(employeeId);
    }

}
