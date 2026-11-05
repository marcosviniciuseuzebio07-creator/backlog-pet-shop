package com.petshop.acrescimo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/** Validação apenas de FORMATO da entrada (campos presentes). Regras de negócio ficam no Service. */
public record AcrescimoRequest(
        @NotBlank(message = "O nome é obrigatório") String nome,
        String descricao,
        @NotNull(message = "O valor é obrigatório") BigDecimal valor) {
}
