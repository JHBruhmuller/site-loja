package com.projeto.loja.Service;

import com.projeto.loja.DTO.VendaDTO;
import com.projeto.loja.DTO.VendaHistoricoDTO;
import com.projeto.loja.DTO.VendaLucroDTO;
import com.projeto.loja.Model.Produto;
import com.projeto.loja.Model.Venda;
import com.projeto.loja.Repository.ProdutoRepository;
import com.projeto.loja.Repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public void registrarVenda(VendaDTO vendaDTO) {
        // Buscar o produto pela ID
        Produto produto = produtoRepository.findById(vendaDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + vendaDTO.produtoId() + " não encontrado!"));

        // Verificar se há estoque suficiente
        if (produto.getQuantidade() < vendaDTO.quantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        // Subtrair a quantidade vendida do estoque
        produto.setQuantidade(produto.getQuantidade() - vendaDTO.quantidade());
        produtoRepository.save(produto);

        // Registrar a venda com o preço atual do produto
        Venda venda = new Venda(produto, vendaDTO.quantidade(), produto.getPreco());
        vendaRepository.save(venda);
    }

    public List<VendaHistoricoDTO> listarHistoricoVendas() {
        return vendaRepository.findAll()
                .stream()
                .map(venda -> new VendaHistoricoDTO(
                        venda.getId(),
                        venda.getProduto().getNome(),
                        venda.getQuantidade(),
                        venda.getPreco(),
                        venda.getDataVenda()))
                .collect(Collectors.toList());
    }

    public List<VendaLucroDTO> listarOperacoesCaixa(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findAllByDataVendaBetween(inicio, fim)
                .stream()
                .map(venda -> {
                    Double lucro = (venda.getProduto().getPreco() * venda.getQuantidade())
                            - (venda.getProduto().getCusto() * venda.getQuantidade());
                    return new VendaLucroDTO(
                            venda.getId(),
                            venda.getProduto().getNome(),
                            venda.getQuantidade(),
                            venda.getProduto().getPreco(),
                            venda.getProduto().getCusto(),
                            lucro,
                            venda.getDataVenda());
                })
                .collect(Collectors.toList());
    }
}
