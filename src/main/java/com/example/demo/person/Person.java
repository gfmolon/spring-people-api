package com.example.demo.person;
import com.example.demo.city.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // aqui vai o espaco pra chave estrangeira da cidade
    @ManyToOne
    @JoinColumn(name = "city_id")   // FK na tabela person
    private City city;

}


