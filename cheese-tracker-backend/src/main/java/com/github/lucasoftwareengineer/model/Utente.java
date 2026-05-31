package com.github.lucasoftwareengineer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Entità che rappresenta un utente del sistema di tracciamento del formaggio.
 */
@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    /** Identificatore univoco dell'utente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ute_id")
    private Long id;

    /** Nome dell'utente. */
    @Column(name = "ute_nome")
    private String nome;

    /** Cognome dell'utente. */
    @Column(name = "ute_cognome")
    private String cognome;

    /** Username per l'autenticazione. */
    @Column(name = "ute_username")
    private String username;

    /** Password cifrata dell'utente. */
    @Column(name = "ute_password")
    private String password;

    /**
     * Un utente può avere più lavorazioni.
     */
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lavorazione> lavorazioni;
}
