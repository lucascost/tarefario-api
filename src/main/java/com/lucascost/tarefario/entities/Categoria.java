package com.lucascost.tarefario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private List<Tarefa> tarefas;

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        Nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(getId(), categoria.getId()) && Objects.equals(getNome(), categoria.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTarefas());
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", Nome='" + Nome + '\'' +
                ", tarefas=" + tarefas +
                '}';
    }
}
