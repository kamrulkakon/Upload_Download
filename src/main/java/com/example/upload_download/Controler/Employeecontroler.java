package com.example.upload_download.Controler;


import com.example.upload_download.Service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Employeecontroler {

    @Autowired
    public Employeeservice employeeservice;


}
