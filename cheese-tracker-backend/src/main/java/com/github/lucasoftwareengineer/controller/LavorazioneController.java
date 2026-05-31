package com.github.lucasoftwareengineer.controller;

import com.github.lucasoftwareengineer.dto.LavorazioneRequest;
import com.github.lucasoftwareengineer.dto.LavorazioneResponse;
import com.github.lucasoftwareengineer.service.LavorazioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller per le operazioni CRUD sulla {@link com.github.lucasoftwareengineer.model.Lavorazione}.
 *
 * Endpoints disponibili:
 * - POST   /api/lavorazioni          → crea una nuova lavorazione
 * - PUT    /api/lavorazioni/{id}     → aggiorna una lavorazione esistente
 * - DELETE /api/lavorazioni/{id}     → elimina una lavorazione
 * - GET    /api/lavorazioni/{id}     → restituisce una lavorazione per ID
 * - GET    /api/lavorazioni          → lista paginata di lavorazioni
 */
@RestController
@RequestMapping("/api/lavorazioni")
@RequiredArgsConstructor
@Validated
public class LavorazioneController {

    private final LavorazioneService lavorazioneService;

    /** Crea una nuova lavorazione. */
    @PostMapping
    public ResponseEntity<LavorazioneResponse> create(@Valid @RequestBody LavorazioneRequest request) {
        LavorazioneResponse created = lavorazioneService.create(request);
        return ResponseEntity.ok(created);
    }

    /** Aggiorna una lavorazione esistente. */
    @PutMapping("/{id}")
    public ResponseEntity<LavorazioneResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody LavorazioneRequest request) {
        LavorazioneResponse updated = lavorazioneService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    /** Elimina una lavorazione per ID. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lavorazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Recupera una lavorazione per ID. */
    @GetMapping("/{id}")
    public ResponseEntity<LavorazioneResponse> get(@PathVariable Long id) {
        LavorazioneResponse resp = lavorazioneService.get(id);
        return ResponseEntity.ok(resp);
    }

    /** Restituisce una pagina di lavorazioni. */
    @GetMapping
    public ResponseEntity<Page<LavorazioneResponse>> list(Pageable pageable) {
        Page<LavorazioneResponse> page = lavorazioneService.list(pageable);
        return ResponseEntity.ok(page);
    }
}
