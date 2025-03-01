package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Entity(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "inventory")
    private Set<Rental> rentals;

    @Column(name = "last_update")
    private Date lastUpdate;
}