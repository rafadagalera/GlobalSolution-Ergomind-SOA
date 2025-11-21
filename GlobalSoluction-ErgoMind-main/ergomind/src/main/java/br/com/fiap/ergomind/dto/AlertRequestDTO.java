package br.com.fiap.ergomind.dto;

import br.com.fiap.ergomind.model.AlertType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating and updating alerts.
 * Used in POST and PUT requests.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequestDTO {

    @NotNull(message = "O tipo de alerta é obrigatório")
    private AlertType alertType;

    @NotEmpty(message = "A mensagem do alerta não pode estar vazia")
    private String message;

    @PositiveOrZero(message = "O nível do alerta deve ser zero ou positivo")
    private double alertLevel;
}

