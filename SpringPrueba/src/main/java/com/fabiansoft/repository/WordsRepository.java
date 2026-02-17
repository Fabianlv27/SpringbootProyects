package com.fabiansoft.repository;

import com.fabiansoft.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    // Metodos de consulta personalizados
}
