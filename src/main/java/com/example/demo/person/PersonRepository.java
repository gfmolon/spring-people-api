package com.example.demo.person;
import org.springframework.data.jpa.repository.JpaRepository;

// Cria interface (PersonRepository)

public interface PersonRepository extends JpaRepository<Person, Integer> {

    // findAll();
    // findById(id);
    // save(Entity);
    // deleteById(id);

}
