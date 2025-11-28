package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    // LISTAR TODAS AS PESSOAS
    public List<Person> listAll() {
        return repository.findAll();
    }

    // BUSCAR UMA PESSOA POR ID
    public Person getOne(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
    }

    // CRIAR UMA PESSOA
    public Person create(Person person) {

        if (person.getName() == null || person.getName().isBlank()) {
            throw new RuntimeException("Nome não pode ser vazio.");
        }

        return repository.save(person);
    }
}
