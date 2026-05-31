package com.github.lucasoftwareengineer.service;

import com.github.lucasoftwareengineer.dto.AuthRequest;
import com.github.lucasoftwareengineer.dto.RegisterRequest;
import com.github.lucasoftwareengineer.model.Utente;
import com.github.lucasoftwareengineer.repository.UtenteRepository;
import com.github.lucasoftwareengineer.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service che gestisce la registrazione e l'autenticazione degli utenti.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Registra un nuovo utente, cripta la password e verifica che lo username sia univoco.
     *
     * @throws IllegalArgumentException se lo username è già presente
     */
    public Utente register(RegisterRequest request) {
        if (utenteRepository.findByUsername(request.getUsername()) != null) {
            throw new IllegalArgumentException("Username già in uso: " + request.getUsername());
        }
        Utente utente = new Utente();
        utente.setNome(request.getNome());
        utente.setCognome(request.getCognome());
        utente.setUsername(request.getUsername());
        // Cripta la password prima di salvarla
        utente.setPassword(passwordEncoder.encode(request.getPassword()));
        return utenteRepository.save(utente);
    }

    /**
     * Autentica l'utente e, se corretto, restituisce un token JWT valido 1h.
     */
    public String authenticate(AuthRequest request) throws AuthenticationException {
        // Lancia un'eccezione se le credenziali non sono valide
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // Se siamo qui, le credenziali sono corrette
        return jwtUtil.generateToken(request.getUsername());
    }
}
