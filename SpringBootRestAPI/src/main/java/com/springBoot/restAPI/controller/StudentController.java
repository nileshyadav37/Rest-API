package com.springBoot.restAPI.controller;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.restAPI.entity.Student;
import com.springBoot.restAPI.repository.StudentRepository;
import com.springBoot.restAPI.services.StudentServices;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentServices studentServices;
	
	//get all students
	//localhost:8080/students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student> students = studentRepository.findAll();
		return students;
	}
	
	//localhost:8080/students/1
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentbyId(@PathVariable int id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
	    if (optionalStudent.isPresent()) {
	        return ResponseEntity.ok(optionalStudent.get()); // Return student with status code 200 (OK)
	    } else {
	        return ResponseEntity.notFound().build(); // Return 404 (Not Found)
	    }
	}
	
	@GetMapping("student/byName/{name}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
	    List<Student> students = studentRepository.findByName(name);
	    if (students.isEmpty()) {
	        return ResponseEntity.notFound().build(); // Return 404 (Not Found)
	    } else {
	        return ResponseEntity.ok(students); // Return students with status code 200 (OK)
	    }
	}
	
	@PostMapping("/student/add")
	public ResponseEntity<?> addNewStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent.getRollNo());
	}
	
	@PutMapping("/student/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        // Update student with data from the updatedStudent object
        student.setName(updatedStudent.getName());
        student.setBranch(updatedStudent.getBranch());
        student.setPercentage(updatedStudent.getPercentage());

        // Save the updated student
        student = studentRepository.save(student);

        // Return the updated student with status code 200 (OK)
        return ResponseEntity.ok(student);
    }
	
	
	@DeleteMapping("/student/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
        	studentRepository.deleteById(id);
            return ResponseEntity.ok().build(); // Return 200 (OK)
        } else {
            return ResponseEntity.notFound().build(); // Return 404 (Not Found)
        }
    }
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String RuntimeExceptionHnadler(Exception ex) {
		return ex.getMessage();
	}
	

}
