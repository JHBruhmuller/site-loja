package com.projeto.loja.Service;

import com.projeto.loja.DTO.AtualizarProdutoDTO;
import com.projeto.loja.DTO.BuscarProdutoDTO;
import com.projeto.loja.DTO.CadastrarProdutoDTO;
import com.projeto.loja.DTO.ListarProdutosDTO;

import java.util.List;
import java.util.Optional;

public interface ProdutoServiceInterface {
    CadastrarProdutoDTO salvarProduto(CadastrarProdutoDTO cadastrarProdutoDTO);

    List<ListarProdutosDTO> listarProdutos();

    Optional<BuscarProdutoDTO> buscarProduto(Long id);

    AtualizarProdutoDTO atualizarProduto(Long id, AtualizarProdutoDTO atualizarProdutoDTO);

    void deletarProduto(Long id);
}
