package com.example.upload_download.Service;

import com.example.upload_download.Entity.Employee;
import com.example.upload_download.Repository.EmployeeRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@Service

public class FileService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final String fileUploadPath = "path/to/your/upload/directory";

    public void saveFile(MultipartFile file) {
        try {
            Employee employee = new Employee();
            employee.setProfile(file.getOriginalFilename());
            employee.setSize(file.getSize());
            employee.setContent(file.getBytes());
            employeeRepository.save(employee);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    public Resource getFile(Long fileId) throws IOException {
        Employee employee = employeeRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(fileUploadPath).resolve(employee.getProfile()).toAbsolutePath();

        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            return (Resource) new UrlResource(filePath.toUri());
        } else {
            throw new RuntimeException("File not found or not readable");
        }
    }

    public List<Employee> getAllFiles() {
        return employeeRepository.findAll();
    }
}

