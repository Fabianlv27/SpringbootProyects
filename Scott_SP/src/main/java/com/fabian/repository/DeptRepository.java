package com.fabian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fabian.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    // Método derivado para buscar por nombre de departamento (ej. "ACCOUNTING")
    Dept findByDname(String dname);
}