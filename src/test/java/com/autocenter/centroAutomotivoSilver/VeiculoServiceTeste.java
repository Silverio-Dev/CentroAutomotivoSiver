package com.autocenter.centroAutomotivoSilver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test 
    void deveBuscarVeiculoPeloId(){
        Veiculos veiculoExistente = new Veiculos();
        veiculoExistente.setId(1L);
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoExistente));
        Veiculos resultado = veiculoService.buscarVeiculoPorId(1L);
        assertEquals(veiculoExistente, resultado);
    }

    @Test 
    void deveAlterarVeiculo(){
        Veiculos veiculos = new Veiculos();
        veiculos.setId(2L);
        veiculos.setMarca("BMW");
        veiculos.setModelo("M3");
        veiculos.setCor("Branco");
        veiculos.setPlaca("RPX2J48");
        veiculos.setAno(2020);
        veiculos.setDisponivel(true);
        when(veiculoRepository.findById(2L)).thenReturn(Optional.of(veiculos));
        when(veiculoRepository.save(veiculos)).thenReturn(veiculos);
        Veiculos resultado = veiculoService.alterarveiculo(2L, veiculos);
        assertEquals(veiculos, resultado);
        verify(veiculoRepository).findById(2L);
        verify(veiculoRepository).save(veiculos);
    
    }

    @Test
    void deveDeletarVeiculo() {
    Long id = 1L;

    Veiculos veiculos = new Veiculos();
    veiculos.setId(id);

    when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculos));

    veiculoService.deletarVeiculo(id);

    verify(veiculoRepository, times(1)).findById(id);
    verify(veiculoRepository, times(1)).delete(veiculos);

    }

    @Test
    void deveBuscarPorMarca() {
    String marca = "BMW";

    Veiculos veiculos = new Veiculos();
    veiculos.setId(1L);
    veiculos.setMarca("BMW");

    when(veiculoRepository.findByMarcaIgnoreCase(marca)).thenReturn(List.of(veiculos));

    List<Veiculos> resultado = veiculoService.buscarPorMarca(marca);

    assertEquals(1, resultado.size());
    assertEquals("BMW", resultado.get(0).getMarca());

    verify(veiculoRepository, times(1)).findByMarcaIgnoreCase(marca);

    }

    @Test 
    void buscarPorModelo(){
        String modelo = "M3";

     Veiculos veiculos = new Veiculos();
     veiculos.setId(1L);
     veiculos.setModelo("M3");
     
     when(veiculoRepository.findByModeloIgnoreCase(modelo)).thenReturn(List.of(veiculos));

     List<Veiculos> resultado = veiculoService.buscarPorModelo(modelo);

      assertEquals(1, resultado.size());
      assertEquals("M3", resultado.get(0).getModelo());

    verify(veiculoRepository, times(1)).findByModeloIgnoreCase(modelo);
    }
    


    @Test
    void deveListarVeiculosDisponiveis() {
    Veiculos veiculo1 = new Veiculos();
    veiculo1.setId(1L);
    veiculo1.setMarca("BMW");
    veiculo1.setModelo("M3");
    veiculo1.setDisponivel(true);

    Veiculos veiculo2 = new Veiculos();
    veiculo2.setId(2L);
    veiculo2.setMarca("Toyota");
    veiculo2.setModelo("Corolla");
    veiculo2.setDisponivel(true);

    when(veiculoRepository.findByDisponivelTrue()).thenReturn(List.of(veiculo1, veiculo2));

    List<Veiculos> resultado = veiculoService.listarDisponiveis();

    assertEquals(2, resultado.size());
    assertTrue(resultado.get(0).getDisponivel());
    assertTrue(resultado.get(1).getDisponivel());

    verify(veiculoRepository, times(1)).findByDisponivelTrue();

    }
}
