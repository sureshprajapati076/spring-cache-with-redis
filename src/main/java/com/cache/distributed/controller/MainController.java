package com.cache.distributed.controller;

import com.cache.distributed.service.MyService;
import com.cache.distributed.dto.Student;
import com.cache.distributed.dto.UpdateStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MyService service;

    @GetMapping("/")
    public List<Student> getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity.ok(service.addStudent(student));
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Student updateStd(@PathVariable Integer id, @RequestBody UpdateStudentDto updateStudentDto){
        return service.updateStudent(id,updateStudentDto);
    }

}
