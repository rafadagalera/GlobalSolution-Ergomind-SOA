package br.com.fiap.ergomind.dto;

import br.com.fiap.ergomind.model.AlertType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for returning alert data in responses.
 * Used in GET requests.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponseDTO {

    private Long id;
    private AlertType alertType;
    private String message;
    private double alertLevel;
    private LocalDateTime dateTime;
}

