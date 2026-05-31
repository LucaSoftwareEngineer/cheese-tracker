package com.github.lucasoftwareengineer.dto;

import lombok.Data;

/**
 * DTO per la registrazione di un nuovo utente.
 */
@Data
public class RegisterRequest {
    private String nome;
    private String cognome;
    private String username;
    private String password;
}
