package com.lucascost.tarefario.dto;

import com.lucascost.tarefario.entities.Tarefa;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class TarefaDTO {
    private Long id;
    private String nome;
    private LocalDateTime datetime_inicio;
    private LocalDateTime datetime_termino;
    private Boolean concluida;

    public TarefaDTO() {
    }

    public TarefaDTO(Tarefa entity) {
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

    public LocalDateTime getDatetime_inicio() {
        return datetime_inicio;
    }

    public void setDatetime_inicio(LocalDateTime datetime_inicio) {
        this.datetime_inicio = datetime_inicio;
    }

    public LocalDateTime getDatetime_termino() {
        return datetime_termino;
    }

    public void setDatetime_termino(LocalDateTime datetime_termino) {
        this.datetime_termino = datetime_termino;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }
}
