package com.github.lucasoftwareengineer.dto;

import lombok.Data;

/**
 * DTO per la creazione e l'aggiornamento di un {@link com.github.lucasoftwareengineer.model.Formaggio}.
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;

/**
 * DTO per la creazione e l'aggiornamento di un {@link com.github.lucasoftwareengineer.model.Formaggio}.
 */
@Data
public class FormaggioRequest {
    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;

    @NotNull(message = "Le calorie sono obbligatorie")
    @Positive(message = "Le calorie devono essere un valore positivo")
    private Integer calorie;
}
