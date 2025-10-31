package com.projetos_de_ger.ger_de_projetos.controller;

import com.projetos_de_ger.ger_de_projetos.model.Tarefa;
import com.projetos_de_ger.ger_de_projetos.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefafaService) {
        this.tarefaService = tarefafaService;
    }
    @GetMapping("/tarefas")
    public List<Tarefa> listarTodas() {
        return tarefaService.listarTodas();
    }
    @PostMapping("/tarefas")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.salvar(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }
    @GetMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try{
            Tarefa tarefaAtualizada = tarefaService.atualizar(id, tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/projetos/{idProjeto}/tarefas")
    public ResponseEntity<List<Tarefa>> listarPorProjeto(@PathVariable Long idProjeto) {
        List<Tarefa> tarefasDoProjeto = tarefaService.buscarPorIdProjeto(idProjeto);

        if (tarefasDoProjeto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarefasDoProjeto);
    }

    @DeleteMapping("/tarefas/{id}")
    public void deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
    }
}
