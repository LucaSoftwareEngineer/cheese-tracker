package com.github.lucasoftwareengineer.controller;

import com.github.lucasoftwareengineer.dto.AuthRequest;
import com.github.lucasoftwareengineer.dto.AuthResponse;
import com.github.lucasoftwareengineer.dto.RegisterRequest;
import com.github.lucasoftwareengineer.dto.RegisterResponse;
import com.github.lucasoftwareengineer.model.Utente;
import com.github.lucasoftwareengineer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller che espone le API di registrazione e autenticazione.
 *
 * - {@code /api/auth/register} restituisce un {@link RegisterResponse} che non contiene la password.
 * - {@code /api/auth/login} restituisce il token JWT valido per 1 ora.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /** Registrazione di un nuovo utente. */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        Utente saved = authService.register(request);
        RegisterResponse resp = new RegisterResponse(
                saved.getId(),
                saved.getNome(),
                saved.getCognome(),
                saved.getUsername()
        );
        return ResponseEntity.ok(resp);
    }

    /** Login – restituisce il token JWT. */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
