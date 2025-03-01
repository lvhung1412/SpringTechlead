package com.example.springBootTechlead.model.entity;

import com.example.springBootTechlead.model.entity.enumData.FilmRatingConverter;
import com.example.springBootTechlead.model.entity.enumData.RoleType;
import com.example.springBootTechlead.model.entity.enumData.RoleTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = RoleTypeConverter.class)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

}
