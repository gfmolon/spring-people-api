package com.example.demo.city;
import org.springframework.data.jpa.repository.JpaRepository;

// Cria interface (CityRepository)

public interface CityRepository extends JpaRepository<City, Integer> {

    // findAll();
    // findById(id);
    // save(Entity);
    // deleteById(id);

}
