package com.projeto.loja.Service;

import com.projeto.loja.DTO.AtualizarProdutoDTO;
import com.projeto.loja.DTO.BuscarProdutoDTO;
import com.projeto.loja.DTO.CadastrarProdutoDTO;
import com.projeto.loja.DTO.ListarProdutosDTO;
import com.projeto.loja.Exception.ProdutoNaoEncontradoException;
import com.projeto.loja.Model.Produto;
import com.projeto.loja.Repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements ProdutoServiceInterface {

        @Autowired
        private ProdutoRepository repository;

        @Override
        public CadastrarProdutoDTO salvarProduto(CadastrarProdutoDTO cadastrarProdutoDTO) {
                Produto produto = new Produto(cadastrarProdutoDTO.nome(), cadastrarProdutoDTO.preco(),
                                cadastrarProdutoDTO.quantidade(),
                                cadastrarProdutoDTO.custo());
                Produto salvo = repository.save(produto);
                return new CadastrarProdutoDTO(salvo.getId(), salvo.getNome(), salvo.getPreco(), salvo.getQuantidade(),
                                salvo.getCusto());
        }

        @Override
        public List<ListarProdutosDTO> listarProdutos() {
                return repository.findAll()
                                .stream()
                                .map(produto -> new ListarProdutosDTO(
                                                produto.getId(),
                                                produto.getNome(),
                                                produto.getPreco(),
                                                produto.getQuantidade(),
                                                produto.getCusto()))
                                .collect(Collectors.toList());
        }

        @Override
        public Optional<BuscarProdutoDTO> buscarProduto(Long id) {
                return repository.findById(id)
                                .map(produto -> new BuscarProdutoDTO(
                                                produto.getId(),
                                                produto.getNome(),
                                                produto.getPreco(),
                                                produto.getQuantidade(),
                                                produto.getCusto()));
        }

        @Override
        public AtualizarProdutoDTO atualizarProduto(Long id, AtualizarProdutoDTO atualizarProduto) {
                Produto produto = repository.findById(id)
                                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                                                "Produto com ID " + id + " não encontrado!"));
                produto.setNome(atualizarProduto.nome());
                produto.setPreco(atualizarProduto.preco());
                produto.setQuantidade(atualizarProduto.quantidade());
                Produto atualizado = repository.save(produto);
                return new AtualizarProdutoDTO(atualizado.getId(), atualizado.getNome(), atualizado.getPreco(),
                                atualizado.getQuantidade(), atualizado.getCusto());
        }

        @Override
        public void deletarProduto(Long id) {
                if (!repository.existsById(id)) {
                        throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado!");
                }
                repository.deleteById(id);
        }
}
