package com.autocenter.centroAutomotivoSilver.controlles;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autocenter.centroAutomotivoSilver.models.Veiculos;
import com.autocenter.centroAutomotivoSilver.service.VeiculoService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/veiculos")
@RequiredArgsConstructor 
public class VeiculoController {

    private final VeiculoService veiculoService;
    
    @PostMapping 
    public ResponseEntity<Veiculos> cadastarVeiculo(@RequestBody Veiculos veiculos){
        Veiculos veiculoNovo = veiculoService.cadastrarVeiculo(veiculos);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoNovo);
    }

    @GetMapping 
    public ResponseEntity<List<Veiculos>> listarTodosVeiculos(){
        return ResponseEntity.ok(veiculoService.listarTodosVeiculos()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculos> buscarVeiculoPorId(@PathVariable Long id){
        return ResponseEntity.ok(veiculoService.buscarVeiculoPorId(id));
    }    
        
    @PutMapping("//{id}")
    public ResponseEntity<Veiculos> alterarVeiculo(@PathVariable Long id, @RequestBody Veiculos veiculos){
        return ResponseEntity.ok(veiculoService.alterarveiculo(id, veiculos));
    }

    @DeleteMapping("{id")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id){
            veiculoService.deletarVeiculo(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/marca")
    public  ResponseEntity<List<Veiculos>> buscarVeiculosPorMarca(@RequestParam String marca){
        return ResponseEntity.ok(veiculoService.buscarPorMarca(marca));
    }

    @GetMapping("/modelo")
    public ResponseEntity<List<Veiculos>> buscarVeiculosPorModelo(@RequestParam String modelo){
        return ResponseEntity.ok(veiculoService.buscarPorModelo(modelo));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Veiculos>> ListasDosDisponiveis(){
        return ResponseEntity.ok(veiculoService.listarDisponiveis());
    }

}
