package com.lucascost.tarefario.repository;

import com.lucascost.tarefario.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByNomeIgnoreCaseContaining(String searchQuery);
}
