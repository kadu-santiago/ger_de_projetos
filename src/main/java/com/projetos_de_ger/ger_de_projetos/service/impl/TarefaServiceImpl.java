package com.projetos_de_ger.ger_de_projetos.service.impl;

import java.util.List;
import com.projetos_de_ger.ger_de_projetos.enums.StatusTarefa;
import com.projetos_de_ger.ger_de_projetos.model.Projeto;
import com.projetos_de_ger.ger_de_projetos.model.Tarefa;
import com.projetos_de_ger.ger_de_projetos.repository.ProjetoRepository;
import com.projetos_de_ger.ger_de_projetos.service.TarefaService;
import com.projetos_de_ger.ger_de_projetos.repository.TarefaRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
        this.tarefaRepository = tarefaRepository;
    }
    @Override
    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        if (tarefa.getProjeto().getId() == null) {
            throw new IllegalArgumentException("A tarefa deve estar associada a um projeto");
        }
        if (tarefa.getStatus() == null) {
            throw new IllegalArgumentException("O status da tarefa é obrigatório.");
        }
        if (tarefa.getId() == null) {
            tarefa.setStatus(StatusTarefa.valueOf("PENDENTE"));
        }
        return tarefaRepository.save(tarefa);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarefa buscarPorId(Long id) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);
        if (tarefaOpt.isEmpty()) {
            throw new RuntimeException("Não foi possível encontrar a tarefa com o id " + id);
        }
        return tarefaOpt.get();
    }
    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> buscarPorIdProjeto(Long id){
        return tarefaRepository.findByProjetoId(id);
    }

    @Override
    @Transactional
    public Tarefa atualizar(Long id, Tarefa tarefaAtt) {

        Tarefa tarefaExistente = buscarPorId(id);
        tarefaExistente.setDescricao(tarefaAtt.getDescricao());
        tarefaExistente.setStatus(tarefaAtt.getStatus());

        return tarefaRepository.save(tarefaExistente);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> listarTodas(){
        List<Tarefa> tarefas = tarefaRepository.findAll();
        for (Tarefa tarefa : tarefas) {
            Hibernate.initialize(tarefa.getProjeto()); // <--- CHAVE DA SOLUÇÃO
        }
        return tarefas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> buscarPorProjetoIdENome(Long projetoId, String nome) {
        return tarefaRepository.findByProjetoIdAndDescricaoContainingIgnoreCase(projetoId, nome);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Tarefa tarefaExistente = buscarPorId(id);
        tarefaRepository.delete(tarefaExistente);
    }
}
