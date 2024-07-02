package com.sami.ems_backend.controller;


import com.sami.ems_backend.dto.EmployeeDto;
import com.sami.ems_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build GET Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return  ResponseEntity.ok(employeeDto);
    }

    // Build GET ALL Employees
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployees();
        return  ResponseEntity.ok(employeeDto);
    }

    // Build UPDATE Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId,employeeDto);
        return  ResponseEntity.ok(updatedEmployeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
         employeeService.deleteEmployee(employeeId);
        return  ResponseEntity.ok("Employee deleted successfully");
    }

}
