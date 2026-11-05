package com.petshop.acrescimo.service;

import com.petshop.acrescimo.dto.AcrescimoRequest;
import com.petshop.acrescimo.dto.AcrescimoResponse;
import com.petshop.acrescimo.exception.RegraNegocioException;
import com.petshop.acrescimo.model.Acrescimo;
import com.petshop.acrescimo.repository.AcrescimoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/** Camada de negócio: TODAS as regras da US05 vivem aqui. */
@Service
public class AcrescimoService {

    private final AcrescimoRepository repository;

    public AcrescimoService(AcrescimoRepository repository) {
        this.repository = repository;
    }

    public AcrescimoResponse cadastrar(AcrescimoRequest request) {
        String nome = request.nome().trim();

        // RN01: o valor do acréscimo deve ser maior que zero
        if (request.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraNegocioException("O valor do acréscimo deve ser maior que zero");
        }
        // RN02: não pode existir outro acréscimo com o mesmo nome
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe um acréscimo com o nome '" + nome + "'");
        }

        Acrescimo salvo = repository.save(new Acrescimo(nome, request.descricao(), request.valor()));
        return paraResponse(salvo);
    }

    public List<AcrescimoResponse> listar() {
        return repository.findAll().stream().map(this::paraResponse).toList();
    }

    private AcrescimoResponse paraResponse(Acrescimo a) {
        return new AcrescimoResponse(a.getId(), a.getNome(), a.getDescricao(), a.getValor());
    }
}
