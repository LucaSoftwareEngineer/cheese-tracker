package com.github.lucasoftwareengineer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO restituito al client dopo una login riuscita.
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
