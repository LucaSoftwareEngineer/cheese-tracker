package com.github.lucasoftwareengineer.dto;

import com.github.lucasoftwareengineer.model.StatoLavorazione;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO per la creazione e l'aggiornamento di una {@link com.github.lucasoftwareengineer.model.Lavorazione}.
 */
@Data
public class LavorazioneRequest {

    /** Data di inizio della lavorazione – obbligatoria e non può essere nel passato. */
    @NotNull(message = "La data di inizio è obbligatoria")
    @FutureOrPresent(message = "La data di inizio non può essere nel passato")
    private LocalDate dataInizio;

    /** Data di fine – opzionale, può essere null. */
    private LocalDate dataFine;

    /** Stato della lavorazione – obbligatorio. */
    @NotNull(message = "Lo stato della lavorazione è obbligatorio")
    private StatoLavorazione statoLavorazione;

    /** Id dell'utente che ha inserito la lavorazione – obbligatorio. */
    @NotNull(message = "L'id dell'utente è obbligatorio")
    private Long utenteId;

    /** Id del formaggio associato – obbligatorio. */
    @NotNull(message = "L'id del formaggio è obbligatorio")
    private Long formaggioId;
}
