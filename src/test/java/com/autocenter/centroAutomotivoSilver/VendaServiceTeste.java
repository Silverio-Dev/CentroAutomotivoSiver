package com.autocenter.centroAutomotivoSilver;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.autocenter.centroAutomotivoSilver.models.Venda;
import com.autocenter.centroAutomotivoSilver.repository.VeiculoRepository;
import com.autocenter.centroAutomotivoSilver.repository.VendaRepository;
import com.autocenter.centroAutomotivoSilver.service.VendaService;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTeste {

      @Mock 
    private VendaRepository vendaRepository;
    private VeiculoRepository veiculoRepository;

    @InjectMocks 
    private VendaService vendaService;

     @Test 
    void deveRealizarVenda(){
        Venda venda = new Venda();
        venda.setId(1L);
        when(vendaRepository.findByCarroId(1L)).thenReturn(venda);
        vendaService.realizarVenda(1L);
        verify(vendaRepository, times(1)).findByCarroId(1L);
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

}