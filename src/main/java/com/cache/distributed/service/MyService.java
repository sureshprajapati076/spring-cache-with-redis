package com.cache.distributed.service;

import com.cache.distributed.dto.Student;
import com.cache.distributed.dto.UpdateStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {


    @Autowired
    private StudentDaoService studentDaoService;

    @Cacheable("allstds")
    public List<Student> getAll() {
        manualDelay("FETCHING FROM DB");
        return studentDaoService.findAll();
    }

    @CacheEvict(value = "allstds", allEntries = true)
    public int addStudent(Student student) {
        return studentDaoService.addStudent(student);
    }

    @Cacheable(key = "#id", value = "std")
    public Student findById(Integer id) {
        manualDelay("FETCHING FOR STUDENT WITH ID: "+id);
        return studentDaoService.findById(id);
    }

    @CachePut(key="#id", value = "std")
    @CacheEvict(value = "allstds", allEntries = true)
    public Student updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
        return studentDaoService.updateStd(id,updateStudentDto);
    }

    private void manualDelay(String msg) {
        System.out.println(msg);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
