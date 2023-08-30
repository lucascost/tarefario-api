package com.lucascost.tarefario.controller;

import com.lucascost.tarefario.dto.CategoriaDTO;
import com.lucascost.tarefario.entities.Categoria;
import com.lucascost.tarefario.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<CategoriaDTO> categorias = categoriaService.findAll();
        if (!categorias.isEmpty()) {
            return ResponseEntity.ok(categorias);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        Categoria novaCategoria = categoriaService.cadastrarCategoria(categoriaDTO);
        if(novaCategoria != null)
            return ResponseEntity.ok("Categoria cadastrada com sucesso!");

        return ResponseEntity.badRequest().body("Falha ao cadastrar a categoria.");

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        boolean atualizacaoBemsucedida = categoriaService.atualizarCategoria(id, categoriaDTO);
        if(atualizacaoBemsucedida)
            return ResponseEntity.ok("Categoria atualizada com sucesso!");

        return ResponseEntity.badRequest().body("Falha ao atualizar a categoria.");

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Long id){
        boolean remocaoBemSucedida = categoriaService.deletarCategoria(id);
        if (remocaoBemSucedida)
            return ResponseEntity.ok("Categoria removida com sucesso!");

        return ResponseEntity.badRequest().body("Falha ao remover a categoria.");
    }

}
