package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer id;

    private String country;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<City> city;

    @Column(name = "last_update")
    private Date lastUpdate;
}
