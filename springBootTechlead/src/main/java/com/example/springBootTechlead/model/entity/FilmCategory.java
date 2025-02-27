package com.example.springBootTechlead.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity(name = "film_category")
@Data
public class FilmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_category_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "last_update")
    private Date lastUpdate;
}
