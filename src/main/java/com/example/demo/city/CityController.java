package com.example.demo.city;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository repository;

    public CityController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CityResponseDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(city -> new CityResponseDTO(
                        city.getId(),
                        city.getName()
                ))
                .toList();
    }
}
