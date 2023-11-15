package com.example.upload_download.Controler;


import com.example.upload_download.Entity.Employee;
import com.example.upload_download.Service.Employeeservice;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class Employeecontroler {

    @Autowired
    public Employeeservice employeeservice;

    @GetMapping("/")
    public String home(Model model)
    {
        List<Employee> list = employeeservice.getAllEmployee();
        model.addAttribute("list", list);
        return "index";
    }
    @PostMapping("/upload")
    public String fileUpload (@RequestParam("file")MultipartFile file, Model model) throws IOException {
       Employee employee = new Employee();
       String fileName = file.getOriginalFilename();
       employee.setProfile(fileName);
       employee.setContent(file.getBytes());
       employee.setSize(file.getSize());
       employeeservice.CreateEmployee(employee);
       model.addAttribute("Success","File Upload Successfully");
       return "index";
    }

    @GetMapping("/downloadfile")
    public Void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws IOException
    {
        Optional<Employee> temp = employeeservice.findEmployeeByID(id);
        if (temp != null)
        {
            Employee employee = temp.get();
            response.setContentType("application/octet-stream");
            String headerKey = "content-Disposition";
            String headerValue = "attachement; filename= " + employee.getProfile();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(employee.getContent());
            outputStream.close();
        }
        return null;
    }



}
