package com.github.lucasoftwareengineer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO restituito al client per un {@link com.github.lucasoftwareengineer.model.Formaggio}.
 */
@Data
@AllArgsConstructor
public class FormaggioResponse {
    private Long id;
    private String descrizione;
    private Integer calorie;
}
