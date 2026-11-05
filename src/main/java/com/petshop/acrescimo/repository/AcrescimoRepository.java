package com.petshop.acrescimo.repository;

import com.petshop.acrescimo.model.Acrescimo;
import org.springframework.data.jpa.repository.JpaRepository;

/** Camada de persistência: só acesso a dados, nenhuma regra de negócio. */
public interface AcrescimoRepository extends JpaRepository<Acrescimo, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
