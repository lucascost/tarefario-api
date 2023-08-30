package com.lucascost.tarefario.dto;

import com.lucascost.tarefario.entities.Categoria;
import com.lucascost.tarefario.entities.Tarefa;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
