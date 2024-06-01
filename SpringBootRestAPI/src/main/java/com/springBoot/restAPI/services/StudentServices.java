package com.springBoot.restAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.springBoot.restAPI.entity.Student;
import com.springBoot.restAPI.repository.StudentRepository;

@Service
public class StudentServices{
	
	@Autowired
	StudentRepository studentRepository;
	

	public List<Student> findByName(String name) {
		List<Student> students = studentRepository.findAll();
		List<Student> result = new ArrayList<>();
		for(Student student : students) {
			if(student.getName().equals(name)) {
				result.add(student);
			}
		}
		return result;
	}
}
