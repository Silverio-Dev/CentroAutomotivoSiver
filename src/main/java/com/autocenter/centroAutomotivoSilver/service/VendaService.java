package com.autocenter.centroAutomotivoSilver.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import com.autocenter.centroAutomotivoSilver.models.Veiculos;
import com.autocenter.centroAutomotivoSilver.models.Venda;
import com.autocenter.centroAutomotivoSilver.repository.VeiculoRepository;
import com.autocenter.centroAutomotivoSilver.repository.VendaRepository;

import jakarta.transaction.Transactional;


@Service 
public class VendaService {
    
    private VendaRepository vendaRepository;
    private VeiculoRepository veiculoRepository;

    public VendaService(VendaRepository vendaRepository, VeiculoRepository veiculoRepository){
        this.vendaRepository = vendaRepository;
        this.veiculoRepository = veiculoRepository;
    }


    public Venda realizarVenda(Long carroId){
        Veiculos veiculos = veiculoRepository.findById(carroId)
        .orElseThrow(()-> new RuntimeException("Carro não encontrado!"));

        if (!veiculos.getDisponivel()) {
                throw new RuntimeException("Carro não esta disponivel!");
        }

        Venda venda = new Venda();
        venda.setVeiculos(veiculos);
        venda.setDataVenda(LocalDate.now());
        venda.setValorTotal(veiculos.getValor());
        veiculos.setDisponivel(false);

        veiculoRepository.save(veiculos);
        return vendaRepository.save(venda);
    }

    public List<Venda> listarVendas(){
        return vendaRepository.findAll();
    }

    public Venda buscarVendaPorId(Long id){
        return vendaRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("venda não encontrada"));
    }

    @Transactional 
    public void cancelarVenda(Long id){
        Venda venda = buscarVendaPorId(id);
        Veiculos veiculos = venda.getVeiculos();
        veiculos.setDisponivel(true);
        veiculoRepository.save(veiculos);
        vendaRepository.delete(venda);
    }
}
