package com.autocenter.centroAutomotivoSilver;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.autocenter.centroAutomotivoSilver.models.Veiculos;
import com.autocenter.centroAutomotivoSilver.models.Venda;
import com.autocenter.centroAutomotivoSilver.repository.VeiculoRepository;
import com.autocenter.centroAutomotivoSilver.repository.VendaRepository;
import com.autocenter.centroAutomotivoSilver.service.VendaService;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTeste {

    @Mock 
    private VendaRepository vendaRepository;

    @Mock 
    private VeiculoRepository veiculoRepository;

    @InjectMocks 
    private VendaService vendaService;


    @Test
    void deveRealizarVenda() {
    Veiculos veiculos = new Veiculos();
    veiculos.setId(1L);
    veiculos.setValor(new BigDecimal("15000.00"));
    veiculos.setDisponivel(true);
    when(veiculoRepository.findById(1L))
            .thenReturn(Optional.of(veiculos));

    Venda venda = new Venda();
    when(vendaRepository.save(any(Venda.class))).thenReturn(venda);
    
    vendaService.realizarVenda(1L);
    assertFalse(veiculos.getDisponivel());

    verify(veiculoRepository, times(1)).findById(1L);
    verify(veiculoRepository, times(1)).save(veiculos);
    verify(vendaRepository, times(1)).save(any(Venda.class));

    }

    @Test 
    void deveListarTodasVendas(){
        List<Venda> vendas = new ArrayList<>();
        Venda venda1 = new Venda();
        Venda venda2 = new Venda();
        Venda venda3 = new Venda();
        vendas.add(venda1);
        vendas.add(venda2);
        vendas.add(venda3);
        when(vendaRepository.findAll()).thenReturn(vendas);
        vendaService.listarVendas();
        verify(vendaRepository, times(1)).findAll();

    }

    @Test
    void deveBuscarVendaPorId(){
        Venda vendaExistente = new Venda();
        vendaExistente.setId(1L);
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(vendaExistente));
        Venda resultado = vendaService.buscarVendaPorId(1L);
        assertEquals(vendaExistente, resultado);
    }

@Test
void deveCancelarVenda() {
    Long id = 1L;

    Veiculos veiculos = new Veiculos();
    veiculos.setId(1L);
    veiculos.setDisponivel(false);

    Venda venda = new Venda();
    venda.setId(id);
    venda.setVeiculos(veiculos);
    when(vendaRepository.findById(id)).thenReturn(Optional.of(venda));

    vendaService.cancelarVenda(id);
    assertTrue(veiculos.getDisponivel());

    verify(veiculoRepository, times(1)).save(veiculos);
    verify(vendaRepository, times(1)).delete(venda);

    }
}