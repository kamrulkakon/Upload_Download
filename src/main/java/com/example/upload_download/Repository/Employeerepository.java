package com.example.upload_download.Repository;

import com.example.upload_download.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employeerepository extends JpaRepository<Employee, Long> {
}
