package com.fabian.repository;

import com.fabian.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fabian.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    Dept findByDname(String dname);

}