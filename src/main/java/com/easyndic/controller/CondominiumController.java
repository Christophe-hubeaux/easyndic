package com.easyndic.controller;

import com.easyndic.model.Condominium;
import com.easyndic.repository.CondominiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/condominium")
public class CondominiumController {
    private final CondominiumRepository condominiumRepository;
    @Autowired
    public CondominiumController(CondominiumRepository condominiumRepository) {
        this.condominiumRepository = condominiumRepository;
    }

    @GetMapping
    public Iterable<Condominium> getAllCondominium() {
        return this.condominiumRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Condominium> getCondominiumById(@PathVariable("id") Integer id) {
        return this.condominiumRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Condominium> createCondominium(@RequestBody Condominium condominium) {
            Condominium newCondominium = condominiumRepository.save(condominium);
            return ResponseEntity.ok(newCondominium);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condominium> updateCondominium(@PathVariable("id") Integer id, @RequestBody Condominium updatedCondominium ) {
        return condominiumRepository.findById(id)
                .map(updatingCondominium -> {
                    updatingCondominium.setName(updatedCondominium.getName());
                    updatingCondominium.setCreationDate(updatedCondominium.getCreationDate());
                    updatingCondominium.setStreet(updatedCondominium.getStreet());
                    updatingCondominium.setStreetNumber(updatedCondominium.getStreetNumber());
                    updatingCondominium.setZipcode(updatedCondominium.getZipcode());
                    updatingCondominium.setCountry(updatedCondominium.getCountry());
                    updatingCondominium.setParcelsNumber(updatedCondominium.getParcelsNumber());
                    Condominium saved = condominiumRepository.save(updatingCondominium);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondominium(@PathVariable("id") Integer id) {
        if (condominiumRepository.existsById(id)) {
            condominiumRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
