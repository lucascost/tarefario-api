package com.lucascost.tarefario.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datetime_inicio;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datetime_termino;

    private Boolean concluida;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tarefa_categoria",
            joinColumns = @JoinColumn(name = "tarefa_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();
    public Tarefa() {
    }

    public Tarefa(Long id, String nome, LocalDateTime datetime_inicio, LocalDateTime datetime_termino, Boolean concluida) {
        this.id = id;
        this.nome = nome;
        this.datetime_inicio = datetime_inicio;
        this.datetime_termino = datetime_termino;
        this.concluida = concluida;
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

    public void addCategoria(Categoria categoria){
        this.categorias.add(categoria);
    }
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ",\n nome='" + nome + "'" +
                ",\n datetime_inicio=" + datetime_inicio +
                ",\n datetime_termino=" + datetime_termino +
                ",\n concluida=" + concluida +
                '}';
    }
}
