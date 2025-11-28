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

   // DELETAR UMA PESSOA 
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ID não encontrado!");
        }
        repository.deleteById(id);
    }

    //ATUALIZAR PESSOA
    public Person update(Integer id, Person nova) {
        Person atual = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Não encontrado"));
        atual.setName(nova.getName());
        return repository.save(atual);
    }

}
