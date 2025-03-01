package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity(name = "language")
@Getter
@Setter
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer id;

    private String name;

    @Transient
    @OneToMany(mappedBy = "language")
    private Set<Film> film1;

    @Transient
    @OneToMany(mappedBy = "originalLanguage")
    private Set<Film> film2;

    @Column(name = "last_update")
    private Date lastUpdate;
}
