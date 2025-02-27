package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E1;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E7;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E4;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E6;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E7;
import com.example.springBootTechlead.model.dto.mysql.Level3.*;
import com.example.springBootTechlead.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "select first_name, last_name \n" +
            "from actor;", nativeQuery = true)
    public List<DTOL1E1> findFirstAndLastName();

    @Query(value = "SELECT a.actor_id, a.first_name, a.last_name\n" +
            "FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
            "GROUP BY a.actor_id, a.first_name, a.last_name\n" +
            "HAVING COUNT(fa.film_id) > 20;", nativeQuery = true)
    public List<DTOL1E7> findNameAppearingMoreThan20Films();

    @Query(value = "SELECT a.actor_id, a.first_name, a.last_name\n" +
            "FROM actor a\n" +
            "\tJOIN film_actor fa ON fa.actor_id = a.actor_id\n" +
            "\tJOIN film f ON f.film_id = fa.film_id\n" +
            "\tJOIN film_category fc ON fc.film_id = f.film_id\n" +
            "GROUP BY a.actor_id\n" +
            "HAVING COUNT(distinct fc.category_id) = (select count(*) from category)\n" +
            "ORDER BY a.actor_id ASC;", nativeQuery = true)
    public List<DTOL2E4> findActorAppearedAtLeast1FilmInEachCategory();

    @Query(value = "SELECT a.actor_id, a.first_name, a.last_name, SUM(f.rental_rate) AS total_revenue\n" +
            "FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
            "\tJOIN film f ON f.film_id = fa.film_id\n" +
            "GROUP BY a.actor_id;", nativeQuery = true)
    public List<DTOL2E6> findTotalRevenueByEachActor();

    @Query(value = "SELECT distinct a.actor_id, a.first_name, a.last_name\n" +
            "FROM actor a\n" +
            "JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
            "JOIN film f ON fa.film_id = f.film_id\n" +
            "WHERE f.rating = 'R'\n" +
            "AND a.actor_id NOT IN (\n" +
            "    SELECT DISTINCT fa.actor_id\n" +
            "    FROM film_actor fa\n" +
            "    JOIN film f ON fa.film_id = f.film_id\n" +
            "    WHERE f.rating = 'G'\n" +
            ");", nativeQuery = true)
    public List<DTOL2E7> findActorApearedAtLeast1FilmRatingRButNotG();

    @Query(value = "SELECT a.first_name, a.last_name, c.`name` AS category_name, AVG(f.rental_duration) AS `avg`\n" +
            "FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
            "\tJOIN film f ON f.film_id = fa.film_id\n" +
            "\tJOIN film_category fc ON fc.film_id = f.film_id\n" +
            "\tJOIN category c ON c.category_id = fc.category_id\n" +
            "GROUP BY a.actor_id, c.category_id\n" +
            "HAVING COUNT(f.film_id) > 0;", nativeQuery = true)
    public List<DTOL3E1> findAverageRentalDuration();

    @Query(value = "SELECT distinct a.actor_id, a.first_name, a.last_name\n" +
            "FROM actor a\n" +
            "JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
            "JOIN film f ON fa.film_id = f.film_id\n" +
            "WHERE f.rating = 'R' AND f.`length` > 120\n" +
            "AND a.actor_id NOT IN (\n" +
            "    SELECT DISTINCT fa.actor_id\n" +
            "    FROM film_actor fa\n" +
            "    JOIN film f ON fa.film_id = f.film_id\n" +
            "    WHERE f.rating = 'G'\n" +
            ");", nativeQuery = true)
    public List<DTOL3E2> findActorAppearedFilmRatingRAnd2HoursButNotG();

    @Query(value = "SELECT \n" +
            "    a1.first_name, \n" +
            "    a1.last_name, \n" +
            "    COUNT(DISTINCT fa1.film_id) AS number_of_film\n" +
            "FROM actor a1\n" +
            "\tJOIN film_actor fa1 ON a1.actor_id = fa1.actor_id\n" +
            "\tJOIN film_actor fa2 ON fa1.film_id = fa2.film_id\n" +
            "\tJOIN actor a2 ON fa2.actor_id = a2.actor_id\n" +
            "WHERE a1.actor_id <> a2.actor_id\n" +
            "GROUP BY a1.actor_id, a1.first_name, a1.last_name\n" +
            "HAVING COUNT(DISTINCT a2.actor_id) = (SELECT COUNT(*) - 1 FROM actor);", nativeQuery = true)
    public List<DTOL3E6> findActorAppearedAtLeast1FilmWithEveryOthers();

    @Query(value = "SELECT a.first_name, a.last_name\n" +
            "FROM actor a\n" +
            "\tJOIN film_actor fa ON fa.actor_id = a.actor_id\n" +
            "\tJOIN film_category fc ON fc.film_id = fa.film_id\n" +
            "\tJOIN category c ON c.category_id = fc.category_id\n" +
            "\tJOIN film f ON f.film_id = fa.film_id\n" +
            "WHERE (f.rating = 'PG-13' AND f.length > 120) OR (f.rating = 'R' AND f.length < 90)\n" +
            "GROUP BY a.actor_id\n" +
            "ORDER BY a.actor_id;", nativeQuery = true)
    public List<DTOL3E10> findActorAppearedFilmRatingPGAndThan2HoursAndRLessThan90Minutes();

}
