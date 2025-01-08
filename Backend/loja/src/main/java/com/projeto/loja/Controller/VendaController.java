package com.projeto.loja.Controller;

import com.projeto.loja.DTO.VendaDTO;
import com.projeto.loja.DTO.VendaHistoricoDTO;
import com.projeto.loja.DTO.VendaLucroDTO;
import com.projeto.loja.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<String> registrarVenda(@RequestBody VendaDTO vendaDTO) {
        vendaService.registrarVenda(vendaDTO);
        return ResponseEntity.ok("Venda registrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<VendaHistoricoDTO>> listarHistoricoVendas() {
        List<VendaHistoricoDTO> historico = vendaService.listarHistoricoVendas();
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/caixa")
    public ResponseEntity<List<VendaLucroDTO>> listarOperacoesCaixa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<VendaLucroDTO> operacoes = vendaService.listarOperacoesCaixa(inicio, fim);
        return ResponseEntity.ok(operacoes);
    }
}
