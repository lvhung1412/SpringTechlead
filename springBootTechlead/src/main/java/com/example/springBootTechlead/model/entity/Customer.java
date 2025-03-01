package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private Integer active;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "customer")
    private Set<Rental> rentals;

    @OneToMany(mappedBy = "customer")
    private Set<Payment> payments;
}
