package com.projeto.loja.DTO;

public record AtualizarProdutoDTO(Long id, String nome, Double preco, Integer quantidade, Double custo) {
}