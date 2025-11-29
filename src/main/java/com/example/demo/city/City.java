package com.example.demo.city;
import com.example.demo.person.Person;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "city") // o dono da relacao é o atributo city dentro de PERSON
    private List<Person> people;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}


