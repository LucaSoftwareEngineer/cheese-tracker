package com.github.lucasoftwareengineer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entità che rappresenta una lavorazione del formaggio.
 *
 * Una lavorazione è collegata all'{@link Utente} che l'ha inserita e al
 * {@link Formaggio} a cui si riferisce. Un utente può avere più lavorazioni
 * e un formaggio può essere associato a più lavorazioni.
 */
@Entity
@Table(name = "lavorazioni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lavorazione {

    /** Identificatore univoco della lavorazione. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lav_id")
    private Long id;

    /** Data di inizio della lavorazione. */
    @Column(name = "lav_data_inizio")
    private LocalDate dataInizio;

    /** Data di fine della lavorazione (può essere null se ancora in corso). */
    @Column(name = "lav_data_fine")
    private LocalDate dataFine;

    /** Stato corrente della lavorazione. */
    @Enumerated(EnumType.STRING)
    @Column(name = "lav_stato_lavorazione")
    private StatoLavorazione statoLavorazione;

    /** L'utente che ha creato questa lavorazione. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lav_utente_id", nullable = false)
    private Utente utente;

    /** Il formaggio a cui questa lavorazione si riferisce. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lav_formaggio_id", nullable = false)
    private Formaggio formaggio;
}
