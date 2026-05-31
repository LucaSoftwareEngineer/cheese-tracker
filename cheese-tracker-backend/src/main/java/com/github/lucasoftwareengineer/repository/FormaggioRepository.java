package com.github.lucasoftwareengineer.repository;

import com.github.lucasoftwareengineer.model.Formaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità {@link Formaggio}.
 */
@Repository
public interface FormaggioRepository extends JpaRepository<Formaggio, Long> {
    // Ricerca per descrizione (case‑insensitive) con paging
    org.springframework.data.domain.Page<Formaggio> findByDescrizioneContainingIgnoreCase(String descrizione, org.springframework.data.domain.Pageable pageable);

}
