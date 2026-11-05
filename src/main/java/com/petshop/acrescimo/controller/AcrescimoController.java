package com.petshop.acrescimo.controller;

import com.petshop.acrescimo.dto.AcrescimoRequest;
import com.petshop.acrescimo.dto.AcrescimoResponse;
import com.petshop.acrescimo.service.AcrescimoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Camada de entrada HTTP: recebe, delega ao Service e devolve. Sem regra de negócio. */
@RestController
@RequestMapping("/acrescimos")
public class AcrescimoController {

    private final AcrescimoService service;

    public AcrescimoController(AcrescimoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcrescimoResponse cadastrar(@Valid @RequestBody AcrescimoRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public List<AcrescimoResponse> listar() {
        return service.listar();
    }
}
