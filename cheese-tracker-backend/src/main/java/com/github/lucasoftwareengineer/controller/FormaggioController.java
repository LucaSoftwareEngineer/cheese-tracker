package com.github.lucasoftwareengineer.controller;

import com.github.lucasoftwareengineer.model.Formaggio;
import com.github.lucasoftwareengineer.dto.FormaggioRequest;
import com.github.lucasoftwareengineer.dto.FormaggioResponse;
import com.github.lucasoftwareengineer.service.FormaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

/**
 * Controller per le operazioni CRUD sul {@link Formaggio}.
 *
 * Endpoints disponibili:
 * - POST   /api/formaggi               → crea un nuovo formaggio
 * - PUT    /api/formaggi/{id}          → aggiorna il formaggio con l'ID fornito
 * - DELETE /api/formaggi/{id}          → elimina il formaggio con l'ID fornito
 * - GET    /api/formaggi/{id}          → restituisce il formaggio con l'ID fornito
 * - GET    /api/formaggi               → lista paginata di formaggi; supporta
 *                                         parametri di paging (`page`, `size`, `sort`)
 *                                         e ricerca `descrizione` (case‑insensitive).
 */

@RestController
@RequestMapping("/api/formaggi")
@RequiredArgsConstructor
@Validated
public class FormaggioController {

    private final FormaggioService formaggioService;

    /** Crea un nuovo formaggio. */
    @PostMapping
    public ResponseEntity<FormaggioResponse> create(@RequestBody FormaggioRequest request) {
        FormaggioResponse created = formaggioService.create(request);
        return ResponseEntity.ok(created);
    }

    /** Aggiorna un formaggio esistente. */
    @PutMapping("/{id}")
    public ResponseEntity<FormaggioResponse> update(@PathVariable Long id, @RequestBody FormaggioRequest request) {
        FormaggioResponse updated = formaggioService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    /** Elimina un formaggio per ID. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formaggioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Recupera un formaggio per ID. */
    @GetMapping("/{id}")
    public ResponseEntity<FormaggioResponse> get(@PathVariable Long id) {
        Formaggio f = formaggioService.get(id);
        FormaggioResponse resp = new FormaggioResponse(f.getId(), f.getDescrizione(), f.getCalorie());
        return ResponseEntity.ok(resp);
    }

    /**
     * Restituisce una pagina di formaggi.
     *
     * Parametri query supportati:
     * - `page` (int, opzionale, default 0)
     * - `size` (int, opzionale, default 20)
     * - `sort` (es. `sort=descrizione,asc`)
     * - `descrizione` (string, opzionale) → filtra per descrizione contenente il valore, case‑insensitive.
     */
    @GetMapping
    public ResponseEntity<Page<FormaggioResponse>> list(
            @RequestParam(required = false) String descrizione,
            Pageable pageable
    ) {
        Page<FormaggioResponse> page;
        if (descrizione != null && !descrizione.isBlank()) {
            page = formaggioService.searchByDescrizione(descrizione, pageable);
        } else {
            page = formaggioService.list(pageable);
        }
        return ResponseEntity.ok(page);
    }
}
