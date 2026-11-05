package com.petshop.acrescimo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.petshop.acrescimo.dto.AcrescimoRequest;
import com.petshop.acrescimo.dto.AcrescimoResponse;
import com.petshop.acrescimo.exception.RegraNegocioException;
import com.petshop.acrescimo.model.Acrescimo;
import com.petshop.acrescimo.repository.AcrescimoRepository;
import com.petshop.acrescimo.service.AcrescimoService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class AcrescimoServiceTest {

    private final AcrescimoRepository repository = mock(AcrescimoRepository.class);
    private final AcrescimoService service = new AcrescimoService(repository);

    @Test
    void deveCadastrarAcrescimoValido() {
        when(repository.existsByNomeIgnoreCase("Perfume")).thenReturn(false);
        when(repository.save(any(Acrescimo.class))).thenAnswer(i -> i.getArgument(0));

        AcrescimoResponse r = service.cadastrar(new AcrescimoRequest("  Perfume ", "Aroma", new BigDecimal("15.00")));

        assertEquals("Perfume", r.nome());
        verify(repository).save(any(Acrescimo.class));
    }

    @Test
    void naoDeveCadastrarValorZeroOuNegativo() {
        assertThrows(RegraNegocioException.class,
                () -> service.cadastrar(new AcrescimoRequest("Laço", null, BigDecimal.ZERO)));
        verify(repository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarNomeDuplicado() {
        when(repository.existsByNomeIgnoreCase("Perfume")).thenReturn(true);
        assertThrows(RegraNegocioException.class,
                () -> service.cadastrar(new AcrescimoRequest("Perfume", null, BigDecimal.TEN)));
        verify(repository, never()).save(any());
    }
}
