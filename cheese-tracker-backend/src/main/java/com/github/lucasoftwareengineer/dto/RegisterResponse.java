package com.github.lucasoftwareengineer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO restituito al client dopo una registrazione riuscita.
 * Contiene tutti i dati dell'utente eccetto la password.
 */
@Data
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String nome;
    private String cognome;
    private String username;
}
