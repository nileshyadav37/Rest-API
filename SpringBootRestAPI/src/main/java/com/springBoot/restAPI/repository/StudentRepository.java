package com.springBoot.restAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.restAPI.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);
}
