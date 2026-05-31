package com.github.lucasoftwareengineer.dto;

import lombok.Data;

/**
 * DTO per la richiesta di login.
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}
