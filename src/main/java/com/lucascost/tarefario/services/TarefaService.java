package com.lucascost.tarefario.services;

import com.lucascost.tarefario.dto.TarefaDTO;
import com.lucascost.tarefario.entities.Categoria;
import com.lucascost.tarefario.entities.Tarefa;
import com.lucascost.tarefario.repository.CategoriaRepository;
import com.lucascost.tarefario.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Transactional(readOnly = true)
    public List<TarefaDTO> findAll(){
        List<Tarefa> result = tarefaRepository.findAll();
        return result.stream().map(TarefaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(TarefaDTO::new).orElse(null);
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

    @Transactional(readOnly = true)
    public List<TarefaDTO> findAllByName(String searchQuery){
        List<Tarefa> tarefas = tarefaRepository.findByNomeIgnoreCaseContaining(searchQuery);
        return tarefas.stream().map(TarefaDTO::new).toList();
    }

    @Transactional
    public boolean atualizarTarefa(Long id, TarefaDTO tarefaDTO){
        Optional<Tarefa> tarefaAtual = tarefaRepository.findById(id);

        if(tarefaAtual.isPresent()) {
            Tarefa tarefa = tarefaAtual.get();
            tarefa.setNome(tarefaDTO.getNome());
            tarefa.setDatetime_inicio(tarefaDTO.getDatetime_inicio());
            tarefa.setDatetime_termino(tarefaDTO.getDatetime_termino());
            tarefa.setConcluida(tarefaDTO.getConcluida());

            List<Categoria> categoriasValidas = new ArrayList<>();
            for (Categoria categoria : tarefaDTO.getCategorias()) {
                Optional<Categoria> categoriaCadastrada = categoriaRepository.findById(categoria.getId());
                if (categoriaCadastrada.isPresent() && categoria.equals(categoriaCadastrada.get()))
                    categoriasValidas.add(categoria);
            }
            tarefa.setCategorias(categoriasValidas);

            tarefaRepository.save(tarefa);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deletarTarefa(Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if(tarefa.isPresent()) {
            tarefaRepository.delete(tarefa.get());
            return true;
        }
        return false;

    }
    @Transactional
    public boolean associarCategoriaTarefa(Long tarefaId, Long categoriaId) {
        List<Categoria> categoriaList;
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElse(null);
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);

        if (tarefa != null && categoria != null) {
            categoriaList = tarefa.getCategorias();
            categoriaList.add(categoria);
            tarefa.setCategorias(categoriaList);
            tarefaRepository.save(tarefa);
            return true;
        }

        return false;
    }

}
