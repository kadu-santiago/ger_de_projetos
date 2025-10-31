package com.projetos_de_ger.ger_de_projetos.model;


import com.projetos_de_ger.ger_de_projetos.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity


@Table(name="tarefa")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tarefa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusTarefa status;

    private LocalDate dataLimite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projeto",nullable=false)

    private Projeto projeto;


}
