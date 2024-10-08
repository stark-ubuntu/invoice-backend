package com.br.challenge.ubuntu.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person", schema = "invoice")
public class Person extends PanacheEntityBase {

    @Id
    private UUID id;
    private String name;
    private String taxId;

    public static List<Person> randomPersons (int bound) {
        return Person.find("ORDER BY RANDOM()").page(0, bound).list();
    }
}
