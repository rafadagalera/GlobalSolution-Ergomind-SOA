package br.com.fiap.ergomind.controller;

import br.com.fiap.ergomind.dto.AlertRequestDTO;
import br.com.fiap.ergomind.dto.AlertResponseDTO;
import br.com.fiap.ergomind.service.AlertService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired //Permite que essa classe tenha acesso a todos os métodos da classe AlertService
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<Object> createAlert(@Valid @RequestBody AlertRequestDTO alertRequestDTO){
        try{
            AlertResponseDTO novoAlerta = alertService.createAlert(alertRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAlerta);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<AlertResponseDTO> readAlerts(){
        return alertService.readAllAlerts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAlert(@PathVariable Long id){
        try{
            AlertResponseDTO searchAlerts = alertService.readAlertById(id);
            return ResponseEntity.status(HttpStatus.OK).body(searchAlerts);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlert(@PathVariable Long id, @Valid @RequestBody AlertRequestDTO alertRequestDTO){
        try{
            AlertResponseDTO updateAlerts = alertService.updateAlert(id, alertRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updateAlerts);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAlert(@PathVariable Long id){
        try{
            alertService.deleteAlertById(id);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }





}
