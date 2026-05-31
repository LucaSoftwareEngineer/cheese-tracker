package com.github.lucasoftwareengineer.service;

import com.github.lucasoftwareengineer.dto.LavorazioneRequest;
import com.github.lucasoftwareengineer.dto.LavorazioneResponse;
import com.github.lucasoftwareengineer.model.Formaggio;
import com.github.lucasoftwareengineer.model.Lavorazione;
import com.github.lucasoftwareengineer.model.Utente;
import com.github.lucasoftwareengineer.repository.FormaggioRepository;
import com.github.lucasoftwareengineer.repository.LavorazioneRepository;
import com.github.lucasoftwareengineer.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service per le operazioni CRUD sulla {@link Lavorazione}.
 */
@Service
@RequiredArgsConstructor
public class LavorazioneService {

    private final LavorazioneRepository repository;
    private final UtenteRepository utenteRepository;
    private final FormaggioRepository formaggioRepository;

    /** Crea una nuova lavorazione. */
    public LavorazioneResponse create(LavorazioneRequest request) {
        Utente utente = utenteRepository.findById(request.getUtenteId())
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + request.getUtenteId()));
        Formaggio formaggio = formaggioRepository.findById(request.getFormaggioId())
                .orElseThrow(() -> new IllegalArgumentException("Formaggio non trovato: " + request.getFormaggioId()));

        Lavorazione l = new Lavorazione();
        l.setDataInizio(request.getDataInizio());
        l.setDataFine(request.getDataFine());
        l.setStatoLavorazione(request.getStatoLavorazione());
        l.setUtente(utente);
        l.setFormaggio(formaggio);
        Lavorazione saved = repository.save(l);
        return mapToResponse(saved);
    }

    /** Aggiorna una lavorazione esistente. */
    public LavorazioneResponse update(Long id, LavorazioneRequest request) {
        Lavorazione existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lavorazione non trovata: " + id));
        // Aggiorna campi
        existing.setDataInizio(request.getDataInizio());
        existing.setDataFine(request.getDataFine());
        existing.setStatoLavorazione(request.getStatoLavorazione());
        // Aggiorna relazioni se cambiano
        if (!existing.getUtente().getId().equals(request.getUtenteId())) {
            Utente utente = utenteRepository.findById(request.getUtenteId())
                    .orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + request.getUtenteId()));
            existing.setUtente(utente);
        }
        if (!existing.getFormaggio().getId().equals(request.getFormaggioId())) {
            Formaggio formaggio = formaggioRepository.findById(request.getFormaggioId())
                    .orElseThrow(() -> new IllegalArgumentException("Formaggio non trovato: " + request.getFormaggioId()));
            existing.setFormaggio(formaggio);
        }
        Lavorazione saved = repository.save(existing);
        return mapToResponse(saved);
    }

    /** Elimina una lavorazione per ID. */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /** Recupera una lavorazione per ID. */
    public LavorazioneResponse get(Long id) {
        Lavorazione l = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lavorazione non trovata: " + id));
        return mapToResponse(l);
    }

    /** Restituisce una pagina di lavorazioni. */
    public Page<LavorazioneResponse> list(Pageable pageable) {
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    /** Mappa l'entità Lavorazione al DTO LavorazioneResponse. */
    private LavorazioneResponse mapToResponse(Lavorazione l) {
        return new LavorazioneResponse(
                l.getId(),
                l.getDataInizio(),
                l.getDataFine(),
                l.getStatoLavorazione(),
                l.getUtente().getId(),
                l.getFormaggio().getId()
        );
    }
}
