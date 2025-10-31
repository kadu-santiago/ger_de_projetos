package com.projetos_de_ger.ger_de_projetos.service;

import com.projetos_de_ger.ger_de_projetos.model.Projeto;
import java.util.List;


public interface ProjetoService {

    Projeto salvar(Projeto projeto);

    List<Projeto> listarTodos();

    Projeto buscarPorId(Long id);

    Projeto atualizar(Projeto projeto);

    void remover(Long id);

}
