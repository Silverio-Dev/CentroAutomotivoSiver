package com.autocenter.centroAutomotivoSilver.controlles;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autocenter.centroAutomotivoSilver.models.Venda;
import com.autocenter.centroAutomotivoSilver.service.VendaService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("vendas")
@RequiredArgsConstructor 
public class VendaController {

    private VendaService vendaService;

    @PostMapping("/carro/{carroId}")
    public ResponseEntity<Venda> realizarVenda(@PathVariable Long carroId){
        return ResponseEntity.ok(vendaService.realizarVenda(carroId));
    }

    @GetMapping 
    public ResponseEntity<List<Venda>> listarTodasVendas(){
        return  ResponseEntity.ok(vendaService.listarVendas());
    }

    @GetMapping("{id}")
    public ResponseEntity<Venda> buscarVendaPeloId(@PathVariable Long id){
        return ResponseEntity.ok(vendaService.buscarVendaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id){
        vendaService.cancelarVenda(id);
        return ResponseEntity.noContent().build();
    }




}

