package com.github.lucasoftwareengineer.dto;

import com.github.lucasoftwareengineer.model.StatoLavorazione;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO restituito al client per una {@link com.github.lucasoftwareengineer.model.Lavorazione}.
 */
@Data
@AllArgsConstructor
public class LavorazioneResponse {
    private Long id;
    private java.time.LocalDate dataInizio;
    private java.time.LocalDate dataFine;
    private StatoLavorazione statoLavorazione;
    private Long utenteId;
    private Long formaggioId;
}
