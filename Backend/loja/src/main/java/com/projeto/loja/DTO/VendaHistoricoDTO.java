package com.projeto.loja.DTO;

import java.time.LocalDateTime;

public record VendaHistoricoDTO(Long vendaId, String produtoNome, Integer quantidade, Double preco,
        LocalDateTime dataVenda) {
}
