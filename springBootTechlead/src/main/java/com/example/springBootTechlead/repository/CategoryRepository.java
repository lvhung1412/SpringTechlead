package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E4;
import com.example.springBootTechlead.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository< Category, Integer> {
    @Query(value = "select c.category_id, c.name,  avg(f.rental_duration) as AverageRentalDuration\n" +
            "from film f join film_category fc on f.film_id = fc.film_id\n" +
            "\t\tjoin category c on fc.category_id = c.category_id\n" +
            "group by c.category_id, c.name;", nativeQuery = true)
    public List<DTOL1E4> findAverageRentalDuration();

}
