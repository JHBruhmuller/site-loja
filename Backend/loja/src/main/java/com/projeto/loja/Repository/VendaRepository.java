package com.projeto.loja.Repository;

import com.projeto.loja.Model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findAllByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);
}
