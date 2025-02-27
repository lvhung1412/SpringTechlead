package com.example.springBootTechlead.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity(name = "store")
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Inventory> inventories;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Staff> staff;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Customer> customers;

    @Column(name = "last_update")
    private Date lastUpdate;
}
