package edu.ap.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;

import edu.ap.spring.redis.RedisService;

@Controller
public class StudentController {

    @Autowired
	private RedisService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/importStudents";
    }

    @GetMapping("/{snummer}")
    @ResponseBody
    public String student(@PathVariable("snummer") String snummer) {
        String naam = service.getKey(snummer);

        return naam;
    }

    @GetMapping("/importStudents")
    public String importStudentsForm(Model model) {
        return "importStudents";
    }

    @PostMapping("/importStudents")
    @ResponseBody
    public String importStudents(Model model, @RequestParam("students") String students) throws IOException{
        Boolean exists = false;
        String[] list = students.split(";");

        for (int i = 0; i < list.length; i+= 2) {
            if(!service.exists(list[i])) {
                service.setKey(list[i], list[i+1]);
            }else{
                exists = true;
                break;
            }
        }
        
        if(exists == true){
            return "Student exists";
        }else{
            return "Imported students";
        }
    }
}
