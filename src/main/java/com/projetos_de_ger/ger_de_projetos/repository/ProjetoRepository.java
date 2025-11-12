package com.projetos_de_ger.ger_de_projetos.repository;

import com.projetos_de_ger.ger_de_projetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByNomeContainingIgnoreCase(String nome);
}