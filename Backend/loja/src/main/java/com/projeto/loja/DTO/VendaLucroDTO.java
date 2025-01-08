package com.projeto.loja.DTO;

import java.time.LocalDateTime;

public record VendaLucroDTO(
        Long vendaId,
        String produtoNome,
        Integer quantidade,
        Double precoVenda,
        Double custoUnitario,
        Double lucro,
        LocalDateTime dataVenda) {
}
