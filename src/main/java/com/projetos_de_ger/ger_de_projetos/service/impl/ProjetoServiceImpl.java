package com.projetos_de_ger.ger_de_projetos.service.impl;

import com.projetos_de_ger.ger_de_projetos.model.Projeto;
import com.projetos_de_ger.ger_de_projetos.service.ProjetoService;
import com.projetos_de_ger.ger_de_projetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    @Transactional
    public Projeto salvar(Projeto projeto){
        if (projeto.getNome() == null || projeto.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("O nome do projeto é obrigatório.");
        }

        if (projeto.getDataCriacao() == null){
            projeto.setDataCriacao(java.time.LocalDate.now());
        }

        return projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projeto> listarTodos(){
        return projetoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Projeto buscarPorId(Long id) {
        Optional<Projeto> projetoOpt = projetoRepository.findById(id);

        if (projetoOpt.isEmpty()){
            throw new RuntimeException("Não foi possível encontrar o projeto pelo com o id "+ id);
        }
        return projetoOpt.get();
    }

    @Override
    @Transactional
    public Projeto atualizar(Projeto projetoAtt) {

        projetoAtt.setNome(projetoAtt.getNome());
        projetoAtt.setDescricao(projetoAtt.getDescricao());

        return projetoRepository.save(projetoAtt);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        projetoRepository.deleteById(id);
    }
}