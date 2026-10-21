package com.autocenter.centroAutomotivoSilver;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.autocenter.centroAutomotivoSilver.models.Veiculos;
import com.autocenter.centroAutomotivoSilver.repository.VeiculoRepository;
import com.autocenter.centroAutomotivoSilver.service.VeiculoService;


@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTeste {
    
    @Mock 
    private VeiculoRepository veiculoRepository;

    @InjectMocks 
    private VeiculoService veiculoService;


    @Test 
    void deveCadastar(){
        Veiculos veiculos = new Veiculos();
        when(veiculoRepository.save(veiculos)).thenReturn(veiculos);
        veiculoService.cadastrarVeiculo(veiculos);
        verify(veiculoRepository, times(1)).save(veiculos);
    }

    @Test 
    void deveListarVeiculos(){
        List<Veiculos> veiculos = new ArrayList<>();
        Veiculos veiculos1 = new Veiculos();
        Veiculos veiculos2 = new Veiculos();
        Veiculos veiculos3 = new Veiculos();
        veiculos.add(veiculos1);
        veiculos.add(veiculos2);
        veiculos.add(veiculos3);
        when(veiculoRepository.findAll()).thenReturn(veiculos);
        veiculoService.listarTodosVeiculos();
        verify(veiculoRepository, times(1)).findAll();
    }
}
