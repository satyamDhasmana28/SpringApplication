package com.satyam.springBasics;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class StudentController {
//    using hashmap as database
    HashMap<Integer,Student> studenDb = new HashMap<>();

    @PostMapping("/add-student")
    public String addStudent(@RequestBody() Student student){
        int key = student.getId();
        if(studenDb.containsKey(key)){
            return "Student id already exists :(";
        }
        studenDb.put(key,student);
        return "Student added successfully :)";
    }

    @GetMapping("/get-students")
    public List<Student> getStudents(){
        List<Student> list = new ArrayList<>();
        for(int key:studenDb.keySet()){
            list.add(studenDb.get(key));
        }
        return list;
    }
    @GetMapping("/get-student-by-id")
    public Student getStudentById(@RequestParam("id") int id){
        return studenDb.get(id);
    }

    @GetMapping("/get-student-by-firstName")
    public Student getStudentByFirstName(@RequestParam("firstName") String firstName){
        for(int key : studenDb.keySet()){
            Student student = studenDb.get(key);
            if(Objects.equals(student.firstName, firstName)){
                return student;
            }
        }
        return null;
    }
    @DeleteMapping("/delete-student-by-id")
    public String deleteStudentById(@RequestParam("id")int id){
        if(null == studenDb.get(id)){
            return "Student doesn't exists with id "+id+" ;(";
        }
        else{
            Student student = studenDb.get(id);
            studenDb.remove(id);
            return student.firstName+" "+student.lastName+" is removed succesfully :(";
        }
    }

}
