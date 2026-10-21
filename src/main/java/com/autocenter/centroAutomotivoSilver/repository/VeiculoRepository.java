package com.autocenter.centroAutomotivoSilver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autocenter.centroAutomotivoSilver.models.Veiculos;

@Repository 
public interface VeiculoRepository extends JpaRepository<Veiculos, Long> {
    
    List<Veiculos> findByMarcaIgnoreCase(String marca);
    List<Veiculos> findByModeloIgnoreCase(String modelo);
    List<Veiculos> findByDisponivelTrue();
    
}
