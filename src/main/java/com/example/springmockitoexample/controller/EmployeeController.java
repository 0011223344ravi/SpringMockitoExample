package com.example.springmockitoexample.controller;

import com.example.springmockitoexample.dao.EmployeeRepository;
import com.example.springmockitoexample.model.Employee;
import com.example.springmockitoexample.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EmployeeService")
public class EmployeeController {

    public int add(int a , int b ){
        int n = a+b;
        return n ;

    }
    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody Employee employee) {
        repository.save(employee);
        return new Response(employee.getId() + " inserted", Boolean.TRUE);
    }

    @GetMapping("/getEmployees")
    public Response getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return new Response("record counts : " + employees.size(), Boolean.TRUE);
    }
}