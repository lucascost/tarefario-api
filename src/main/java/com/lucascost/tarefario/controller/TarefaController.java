package com.lucascost.tarefario.controller;

import com.lucascost.tarefario.dto.TarefaDTO;
import com.lucascost.tarefario.entities.Tarefa;
import com.lucascost.tarefario.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> findAll(){
        List<TarefaDTO> tarefaList = tarefaService.findAll();
        if (!tarefaList.isEmpty()) {
            return ResponseEntity.ok(tarefaList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable Long id) {
        TarefaDTO tarefa = tarefaService.findById(id);
        if (tarefa != null)
            return ResponseEntity.ok(tarefa);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> cadastrarTarefa(@RequestBody TarefaDTO tarefaDTO){
        Tarefa novaTarefa = tarefaService.cadastrarTarefa(tarefaDTO);
        if (novaTarefa != null) {
            return ResponseEntity.ok("Tarefa cadastrada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Falha ao cadastrar a tarefa.");
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<String> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO){
        boolean atualizacaoBemSucedida = tarefaService.atualizarTarefa(id, tarefaDTO);
        if (atualizacaoBemSucedida) {
            return ResponseEntity.ok("Tarefa atualizada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Falha ao atualizar a tarefa.");
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<String> deletarTarefa(@PathVariable Long id ){
        boolean remocaoBemSucedida = tarefaService.deletarTarefa(id);
        if (remocaoBemSucedida) {
            return ResponseEntity.ok("Tarefa removida com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Falha ao remover a tarefa.");
        }
    }
}
