package com.cache.distributed.service;

import com.cache.distributed.dto.Student;
import com.cache.distributed.dto.UpdateStudentDto;
import com.cache.distributed.exception.NoStudentFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoService {

    @Autowired
    private JdbcClient jdbcClient;

    public int addStudent(Student student){
        return jdbcClient.sql("INSERT INTO MYSTUDENTS (ID,NAME,AGE) VALUES(:studentID,:studentName,:studentAge)")
                .params(Map.of(
                        "studentID", student.id(),
                        "studentName",student.name(),
                        "studentAge",student.age()
                ))
                .update();
    }


    public List<Student> findAll() {
        return jdbcClient.sql("SELECT * FROM MYSTUDENTS")
                .query(Student.class)
                .list();
    }

    public Student findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM MYSTUDENTS WHERE ID=?")
                .params(id)
                .query(Student.class)
                .optional()
                .orElseThrow(()->new NoStudentFoundException("Student Not Found"));
    }

    public Student updateStd(Integer id, UpdateStudentDto updateStudentDto) {
        var dbStudent = findById(id);
        var result = jdbcClient.sql("UPDATE MYSTUDENTS SET NAME=:studentName, AGE=:studentAge WHERE ID=:studentId")
                .params(Map.of(
                        "studentId",id,
                        "studentName",updateStudentDto.name()==null?dbStudent.name():updateStudentDto.name(),
                        "studentAge",updateStudentDto.age()==null?dbStudent.age():updateStudentDto.age()))
                .update();
        if(result==1){
            return new Student(id,updateStudentDto.name()==null?dbStudent.name():updateStudentDto.name(),updateStudentDto.age()==null?dbStudent.age():updateStudentDto.age());
        }
        else{
            return dbStudent;
        }
    }
}
