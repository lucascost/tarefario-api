package com.lucascost.tarefario.services;

import com.lucascost.tarefario.dto.CategoriaDTO;
import com.lucascost.tarefario.dto.TarefaDTO;
import com.lucascost.tarefario.entities.Categoria;
import com.lucascost.tarefario.entities.Tarefa;
import com.lucascost.tarefario.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll(){
        List<Categoria> result = categoriaRepository.findAll();
        return result.stream().map(CategoriaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id){
        Optional<Categoria> result = categoriaRepository.findById(id);
        return result.map(CategoriaDTO::new).orElse(null);
    }

    @Transactional
    public Categoria cadastrarCategoria(CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
        return categoriaRepository.save(novaCategoria);
    }

    @Transactional
    public boolean atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setNome(categoriaDTO.getNome());
            categoriaRepository.save(categoria);
            return true;
        }
        return false;
    }

    public boolean deletarCategoria(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent()){
            categoriaRepository.delete(categoria.get());
            return true;
        }
        return false;
    }

}
