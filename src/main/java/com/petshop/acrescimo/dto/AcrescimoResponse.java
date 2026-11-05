package com.petshop.acrescimo.dto;

import java.math.BigDecimal;

public record AcrescimoResponse(Long id, String nome, String descricao, BigDecimal valor) {
}
