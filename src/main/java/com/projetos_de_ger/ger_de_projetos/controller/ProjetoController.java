package com.projetos_de_ger.ger_de_projetos.controller;

import com.projetos_de_ger.ger_de_projetos.model.Projeto;
import com.projetos_de_ger.ger_de_projetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/projetos")
public class ProjetoController{

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public List<Projeto> listar(){
        return projetoService.listarTodos();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Projeto>> buscarPorNome(@PathVariable String nome) {
        List<Projeto> projetos = projetoService.buscarPorNome(nome);
        if (projetos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projetos);
    }

    @PostMapping
    public ResponseEntity<Projeto> salvar(@RequestBody Projeto projeto){

        Projeto novoProjeto = projetoService.salvar(projeto);
        return new ResponseEntity<>(novoProjeto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id){
        try{
            Projeto projeto = projetoService.buscarPorId(id);
            return ResponseEntity.ok(projeto);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto){
        try{
            Projeto projetoAtualizado = projetoService.atualizar(projeto);
            return ResponseEntity.ok(projetoAtualizado);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id){
        projetoService.remover(id);
    }

}
