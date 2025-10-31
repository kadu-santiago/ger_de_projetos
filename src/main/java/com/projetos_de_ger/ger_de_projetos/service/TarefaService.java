package com.projetos_de_ger.ger_de_projetos.service;

import com.projetos_de_ger.ger_de_projetos.model.Tarefa;
import java.util.List;

public interface TarefaService {

    Tarefa salvar(Tarefa tarefa);

    List<Tarefa> buscarPorIdProjeto(Long projetoId);

    List<Tarefa> listarTodas();

    Tarefa buscarPorId(Long id);

    Tarefa atualizar(Long id, Tarefa tarefa);

    void deletar(Long id);


}
