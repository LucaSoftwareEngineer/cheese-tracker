package com.github.lucasoftwareengineer.service;

import com.github.lucasoftwareengineer.model.Formaggio;
import com.github.lucasoftwareengineer.dto.FormaggioRequest;
import com.github.lucasoftwareengineer.dto.FormaggioResponse;
import com.github.lucasoftwareengineer.repository.FormaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service per le operazioni CRUD sul {@link Formaggio}.
 */
@Service
@RequiredArgsConstructor
public class FormaggioService {

    public final FormaggioRepository repository;

    /** Crea un nuovo formaggio. */
    public FormaggioResponse create(FormaggioRequest request) {
        Formaggio formaggio = new Formaggio();
        formaggio.setDescrizione(request.getDescrizione());
        formaggio.setCalorie(request.getCalorie());
        Formaggio saved = repository.save(formaggio);
        return new FormaggioResponse(saved.getId(), saved.getDescrizione(), saved.getCalorie());
    }

    /** Aggiorna un formaggio esistente. */
    public FormaggioResponse update(Long id, FormaggioRequest request) {
        Formaggio esistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formaggio non trovato: " + id));
        esistente.setDescrizione(request.getDescrizione());
        esistente.setCalorie(request.getCalorie());
        Formaggio saved = repository.save(esistente);
        return new FormaggioResponse(saved.getId(), saved.getDescrizione(), saved.getCalorie());
    }

    /** Elimina un formaggio per id. */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /** Recupera un formaggio per id. */
    public Formaggio get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formaggio non trovato: " + id));
    }

    /** Restituisce una pagina di formaggi. */
    public Page<FormaggioResponse> list(Pageable pageable) {
        return repository.findAll(pageable)
                .map(f -> new FormaggioResponse(f.getId(), f.getDescrizione(), f.getCalorie()));
    }

    /**
     * Restituisce una pagina di formaggi filtrati per descrizione (case‑insensitive).
     */
    public Page<FormaggioResponse> searchByDescrizione(String descrizione, Pageable pageable) {
        return repository.findByDescrizioneContainingIgnoreCase(descrizione, pageable)
                .map(f -> new FormaggioResponse(f.getId(), f.getDescrizione(), f.getCalorie()));
    }
}
