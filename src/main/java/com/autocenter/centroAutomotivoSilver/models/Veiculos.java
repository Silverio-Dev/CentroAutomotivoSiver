package com.autocenter.centroAutomotivoSilver.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "Carros")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Veiculos {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;
    private String cor;
    private String placa;
    private Boolean disponivel;
}
