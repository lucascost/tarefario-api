package com.lucascost.tarefario.repository;

import com.lucascost.tarefario.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
