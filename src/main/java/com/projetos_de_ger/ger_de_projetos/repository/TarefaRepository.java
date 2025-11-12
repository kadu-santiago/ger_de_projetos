package com.projetos_de_ger.ger_de_projetos.repository;

import com.projetos_de_ger.ger_de_projetos.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByProjetoId(Long projetoId);

    List<Tarefa> findByProjetoIdAndDescricaoContainingIgnoreCase(Long projetoId, String descricao);

    Long id(Long id);
}
