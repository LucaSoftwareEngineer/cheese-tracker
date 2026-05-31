package com.github.lucasoftwareengineer.security;

import com.github.lucasoftwareengineer.model.Utente;
import com.github.lucasoftwareengineer.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service che carica i dettagli dell'utente per Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        }
        return new CustomUserDetails(utente);
    }
}
