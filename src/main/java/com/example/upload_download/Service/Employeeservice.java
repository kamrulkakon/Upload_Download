package com.example.upload_download.Service;

import com.example.upload_download.Entity.Employee;
import com.example.upload_download.Repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Employeeservice {
    @Autowired
    private Employeerepository repository;
    public Employee CreateEmployee (Employee employee)
    {
        return repository.save(employee);
    }
    public List<Employee> getAllEmployee()
    {
        return repository.findAll();
    }
    public Optional<Employee> findEmployeeByID(long id)
    {
        return repository.findById(id);
    }

}
