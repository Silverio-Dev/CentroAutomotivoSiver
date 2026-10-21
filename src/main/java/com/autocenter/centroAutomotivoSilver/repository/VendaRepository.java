package com.autocenter.centroAutomotivoSilver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autocenter.centroAutomotivoSilver.models.Venda;

public interface VendaRepository extends JpaRepository<Long, Venda> {

    List<Venda> findByCarroId(Long carroId);

    

}
