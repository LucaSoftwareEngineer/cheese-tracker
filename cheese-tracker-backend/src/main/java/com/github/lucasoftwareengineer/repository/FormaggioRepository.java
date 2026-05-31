package com.github.lucasoftwareengineer.repository;

import com.github.lucasoftwareengineer.model.Formaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità {@link Formaggio}.
 */
@Repository
public interface FormaggioRepository extends JpaRepository<Formaggio, Long> {

}
