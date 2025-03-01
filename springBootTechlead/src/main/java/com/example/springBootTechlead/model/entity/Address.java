package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.sql.Date;
import java.util.Set;

@Entity(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    private String address;

    private String address2;

    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    private String phone;

    @Column(name = "location", columnDefinition = "POINT")
    private Point location;

    @OneToMany(mappedBy = "address")
    private Set<Store> stores;

    @OneToMany(mappedBy = "address")
    private Set<Staff> staffs;

    @OneToMany(mappedBy = "address")
    private Set<Customer> customers;

    @Column(name = "last_update")
    private Date lastUpdate;
}
