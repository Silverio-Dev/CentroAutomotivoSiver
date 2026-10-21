package com.autocenter.centroAutomotivoSilver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autocenter.centroAutomotivoSilver.models.Veiculos;
import com.autocenter.centroAutomotivoSilver.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    

    public Veiculos cadastrarVeiculo(Veiculos veiculos){
        veiculos.setDisponivel(true);
        return veiculoRepository.save(veiculos);
    }

    public List<Veiculos> listarTodosVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculos buscarVeiculoPorId(Long id){
        return veiculoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("veiculo não encontrado"));
    }

    public Veiculos alterarveiculo(Long id, Veiculos dados){
        Veiculos veiculos = buscarVeiculoPorId(id);
        veiculos.setMarca(dados.getMarca());
        veiculos.setModelo(dados.getModelo());
        veiculos.setAno(dados.getAno());
        veiculos.setValor(dados.getValor());
        veiculos.setCor(dados.getCor());
        veiculos.setAno(dados.getAno());
        veiculos.setPlaca(dados.getPlaca());

        return veiculoRepository.save(veiculos);
    }

    public void deletarVeiculo(Long id){
        Veiculos veiculos = buscarVeiculoPorId(id);
        veiculoRepository.delete(veiculos);
    }

    public List<Veiculos> buscarPorMarca(String marca){
        return veiculoRepository.findByMarcaIgnoreCase(marca);
    }

    public List<Veiculos> buscarPorModelo(String modelo){
        return veiculoRepository.findByModeloIgnoreCase(modelo);
    }

    public List<Veiculos> listarDisponiveis(){
        return veiculoRepository.findByDisponivelTrue();
    }
    
}
