package com.example.demo.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    // GET /people  -> lista todas as pessoas (já em DTO de resposta)
    @GetMapping
    public List<PersonResponseDTO> listAll() {
        return service.listAll();
    }

    // GET /people/{id} -> retorna 1 pessoa pelo id
    @GetMapping("/{id}")  // soma com o /people do @RequestMapping
    public ResponseEntity<PersonResponseDTO> getOne(@PathVariable Integer id) {
        try {
            PersonResponseDTO person = service.getOne(id);
            return ResponseEntity.ok(person);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /people -> cria uma nova pessoa a partir do DTO de entrada
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonRequestDTO dto) {
        try {
            PersonResponseDTO saved = service.create(dto);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /people/{id} -> remove a pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /people/{id} -> atualiza name/city da pessoa
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody PersonRequestDTO dto) {

        try {
            PersonResponseDTO updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
