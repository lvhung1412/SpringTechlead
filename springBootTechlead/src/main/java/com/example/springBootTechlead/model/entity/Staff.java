package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity(name = "staff")
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;

    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private Integer active;

    private String username;

    private String password;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<Store> stores;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Rental> rentals;

    @Column(name = "last_update")
    private Date lastUpdate;
}
