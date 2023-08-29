package com.lucascost.tarefario.repository;

import com.lucascost.tarefario.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
