package br.com.fiap.ergomind.service;

import br.com.fiap.ergomind.dto.AlertRequestDTO;
import br.com.fiap.ergomind.dto.AlertResponseDTO;
import br.com.fiap.ergomind.model.AlertModel;
import br.com.fiap.ergomind.repository.AlertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Essa classe é um componente de servico, que contem a lógica da aplicacao
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public AlertResponseDTO createAlert(AlertRequestDTO alertRequestDTO){
        AlertModel alert = convertToModel(alertRequestDTO);
        AlertModel savedAlert = alertRepository.save(alert);
        return convertToResponseDTO(savedAlert);
    }

    public List<AlertResponseDTO> readAllAlerts(){
        return alertRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public AlertResponseDTO readAlertById(Long id){
        AlertModel alert = alertRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Alerta não encontrado"));
        return convertToResponseDTO(alert);
    }

    public AlertResponseDTO updateAlert(Long id, AlertRequestDTO alertRequestDTO){
        return alertRepository.findById(id).map(existingAlert -> {
            existingAlert.setAlertType(alertRequestDTO.getAlertType());
            existingAlert.setMessage(alertRequestDTO.getMessage());
            existingAlert.setAlertLevel(alertRequestDTO.getAlertLevel());
            AlertModel updatedAlert = alertRepository.save(existingAlert);
            return convertToResponseDTO(updatedAlert);
        }).orElseThrow(()-> new EntityNotFoundException("Alerta não encontrado"));
    }

    public void deleteAlertById(Long id){
        try{
            alertRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Alerta não encontrado");
        }
    }

    // Conversion methods
    private AlertModel convertToModel(AlertRequestDTO dto) {
        AlertModel model = new AlertModel();
        model.setAlertType(dto.getAlertType());
        model.setMessage(dto.getMessage());
        model.setAlertLevel(dto.getAlertLevel());
        return model;
    }

    private AlertResponseDTO convertToResponseDTO(AlertModel model) {
        AlertResponseDTO dto = new AlertResponseDTO();
        dto.setId(model.getId());
        dto.setAlertType(model.getAlertType());
        dto.setMessage(model.getMessage());
        dto.setAlertLevel(model.getAlertLevel());
        dto.setDateTime(model.getDateTime());
        return dto;
    }
}
