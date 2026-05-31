package com.github.lucasoftwareengineer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Entità che rappresenta un tipo di formaggio.
 */
@Entity
@Table(name = "formaggi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formaggio {

    /** Identificatore univoco del formaggio. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "for_id")
    private Long id;

    /** Descrizione del formaggio. */
    @Column(name = "for_descrizione")
    private String descrizione;

    /** Calorie per unità del formaggio. */
    @Column(name = "for_calorie")
    private Integer calorie;

    /**
     * Un formaggio può essere associato a più lavorazioni.
     */
    @OneToMany(mappedBy = "formaggio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lavorazione> lavorazioni;
}
