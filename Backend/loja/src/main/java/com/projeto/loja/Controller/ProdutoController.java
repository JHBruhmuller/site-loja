package com.projeto.loja.Controller;

import com.projeto.loja.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.projeto.loja.DTO.AtualizarProdutoDTO;
import com.projeto.loja.DTO.BuscarProdutoDTO;
import com.projeto.loja.DTO.CadastrarProdutoDTO;
import com.projeto.loja.DTO.ListarProdutosDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<CadastrarProdutoDTO> cadastrarProduto(@RequestBody CadastrarProdutoDTO cadastrarProdutoDTO) {
        CadastrarProdutoDTO salvo = service.salvarProduto(cadastrarProdutoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ListarProdutosDTO>> listarProdutos() {
        return ResponseEntity.ok(service.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarProdutoDTO> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.of(service.buscarProduto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarProdutoDTO> atualizarProduto(@PathVariable Long id,
            @RequestBody AtualizarProdutoDTO atualizarProdutoDTO) {
        AtualizarProdutoDTO atualizado = service.atualizarProduto(id, atualizarProdutoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
