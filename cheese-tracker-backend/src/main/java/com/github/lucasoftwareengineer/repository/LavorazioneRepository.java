package com.github.lucasoftwareengineer.repository;

import com.github.lucasoftwareengineer.model.Lavorazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità {@link Lavorazione}.
 */
@Repository
public interface LavorazioneRepository extends JpaRepository<Lavorazione, Long> {

}
