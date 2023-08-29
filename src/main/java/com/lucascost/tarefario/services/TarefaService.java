package com.lucascost.tarefario.services;

import com.lucascost.tarefario.dto.TarefaDTO;
import com.lucascost.tarefario.entities.Tarefa;
import com.lucascost.tarefario.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public List<TarefaDTO> findAll(){
        List<Tarefa> result = tarefaRepository.findAll();
        return result.stream().map(TarefaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        Tarefa result;
        if (tarefa.isPresent()) {
            result = tarefa.get();
        } else {
            return null;
        }
        return new TarefaDTO(result);
    }

    @Transactional
    public Tarefa cadastrarTarefa(TarefaDTO tarefaDTO){
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setNome(tarefaDTO.getNome());
        novaTarefa.setDatetime_inicio(tarefaDTO.getDatetime_inicio());
        novaTarefa.setDatetime_termino(tarefaDTO.getDatetime_termino());
        novaTarefa.setConcluida(tarefaDTO.getConcluida());

        return  tarefaRepository.save(novaTarefa);
    }

    @Transactional
    public boolean atualizarTarefa(Long id, TarefaDTO tarefaDTO){
        Optional<Tarefa> tarefaAtual = tarefaRepository.findById(id);

        if(tarefaAtual.isPresent()){
            Tarefa tarefa = tarefaAtual.get();
            tarefa.setNome(tarefaDTO.getNome());
            tarefa.setDatetime_inicio(tarefaDTO.getDatetime_inicio());
            tarefa.setDatetime_termino(tarefaDTO.getDatetime_termino());
            tarefa.setConcluida(tarefaDTO.getConcluida());

            tarefaRepository.save(tarefa);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deletarTarefa(Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if(tarefa.isPresent()) {
            tarefaRepository.delete(tarefa.get());
            return true;
        } else return false;

    }
}
