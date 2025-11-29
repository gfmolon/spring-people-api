package com.example.demo.person;

import com.example.demo.city.City;
import com.example.demo.city.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final CityRepository cityRepository;

    public PersonService(PersonRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    // LISTAR TODAS AS PESSOAS
    public List<PersonResponseDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // CRIAR NOVA PESSOA
    public PersonResponseDTO create(PersonRequestDTO dto) {

        City city = cityRepository.findById(dto.cityId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Person p = new Person();
        p.setName(dto.name());
        p.setCity(city);

        Person saved = repository.save(p);

        return toResponse(saved);
    }

    // BUSCAR 1 PESSOA POR ID
    public PersonResponseDTO getOne(Integer id) {

        Person p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        return toResponse(p);
    }

    // ATUALIZAR PESSOA (name + city)
    public PersonResponseDTO update(Integer id, PersonRequestDTO dto) {

        Person p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        City city = cityRepository.findById(dto.cityId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        p.setName(dto.name());
        p.setCity(city);

        Person updated = repository.save(p);

        return toResponse(updated);
    }

    // DELETAR PESSOA
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        repository.deleteById(id);
    }

    // --------- MÉTODO AUXILIAR PARA CONVERTER ENTITY -> DTO ---------
    private PersonResponseDTO toResponse(Person p) {
        return new PersonResponseDTO(
                p.getId(),
                p.getName(),
                p.getCity() != null ? p.getCity().getId() : null
        );
    }
}
