package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E2;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E3;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E8;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E10;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E3;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E8;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E5;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E8;
import com.example.springBootTechlead.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "select title, rental_rate, replacement_cost\n" +
            "from film;", nativeQuery = true)
    public List<DTOL1E2> findTiltesAndRentalRate();

    @Query(value = "select f.film_id, f.title, count(r.rental_id) as number_of_rent\n" +
            "from film f join inventory i on f.film_id = i.film_id\n" +
            "\t\tjoin rental r on i.inventory_id = r.inventory_id\n" +
            "group by f.film_id, f.title\n" +
            "order by number_of_rent DESC\n" +
            "LIMIT 5;", nativeQuery = true)
    public List<DTOL1E3> findTopFiveFilm();

    @Query(value = "SELECT f.title, f.rating, f.`length`\n" +
            "FROM film f\n" +
            "WHERE rating = 'PG-13' AND f.length > 120;", nativeQuery = true)
    public List<DTOL1E8> findTitlesRatingPG13andMoreThan120();

    @Query(value = "SELECT f.film_id, f.title\n" +
            "FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
            "\t\tJOIN film f ON f.film_id = i.film_id \n" +
            "WHERE r.return_date IS NULL;", nativeQuery = true)
    public List<DTOL2E3> findFilmNeverReturn();

    @Query(value = "SELECT f.title, COUNT(DISTINCT r.customer_id) AS 'total_customer'\n" +
            "FROM film f\n" +
            "JOIN inventory i ON i.film_id = f.film_id\n" +
            "JOIN rental r ON r.inventory_id = i.inventory_id\n" +
            "JOIN customer c ON c.customer_id = r.customer_id\n" +
            "GROUP BY f.film_id\n" +
            "HAVING COUNT(DISTINCT r.customer_id) > 50;", nativeQuery = true)
    public List<DTOL2E8> findFilmRentedByMoreThan50CustomesButNeverRentedBySameThan1();

    @Query(value = "SELECT f.title\n" +
            "FROM film f\n" +
            "JOIN inventory i ON f.film_id = i.film_id\n" +
            "JOIN rental r ON i.inventory_id = r.inventory_id\n" +
            "JOIN customer c ON r.customer_id = c.customer_id\n" +
            "WHERE r.customer_id IN (\n" +
            "    -- Lấy danh sách khách hàng đã từng thuê ít nhất một phim thuộc thể loại 'Action'\n" +
            "    SELECT DISTINCT r2.customer_id\n" +
            "    FROM rental r2\n" +
            "    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id\n" +
            "    JOIN film_category fc ON i2.film_id = fc.film_id\n" +
            "    JOIN category c2 ON fc.category_id = c2.category_id\n" +
            "    WHERE c2.name = 'Action'\n" +
            ")\n" +
            "GROUP BY f.film_id\n" +
            "HAVING COUNT(DISTINCT r.customer_id) = COUNT(c.customer_id); ", nativeQuery = true)
    public List<DTOL2E10> findFilmRentedByEveryCustomerWhoNeverRentedAction();

    @Query(value = "SELECT \n" +
            "    f.title AS film_title, \n" +
            "    c.first_name, \n" +
            "    c.last_name, \n" +
            "    DATE(r.rental_date) AS rental_day, \n" +
            "    COUNT(*) AS rental_count\n" +
            "FROM rental r\n" +
            " \tJOIN inventory i ON r.inventory_id = i.inventory_id\n" +
            "\tJOIN film f ON i.film_id = f.film_id\n" +
            "\tJOIN customer c ON r.customer_id = c.customer_id\n" +
            "GROUP BY c.customer_id, f.film_id, DATE(r.rental_date)\n" +
            "HAVING COUNT(*) > 1;", nativeQuery = true)
    public List<DTOL3E5> findFilmRentedBySameCustomerThanOnceInSingleDay();

    @Query(value = "SELECT f.title, COUNT(r.rental_id) AS total_rentals\n" +
            "FROM film f\n" +
            "JOIN inventory i ON f.film_id = i.film_id\n" +
            "JOIN rental r ON i.inventory_id = r.inventory_id\n" +
            "JOIN customer c ON r.customer_id = c.customer_id\n" +
            "WHERE f.film_id NOT IN (\n" +
            "    SELECT DISTINCT r1.inventory_id\n" +
            "    FROM rental r1\n" +
            "    JOIN inventory i1 ON r1.inventory_id = i1.inventory_id\n" +
            "    JOIN film f1 ON i1.film_id = f1.film_id\n" +
            "    WHERE f1.rating = 'G'\n" +
            "    AND r1.customer_id IN (\n" +
            "        SELECT DISTINCT r2.customer_id\n" +
            "        FROM rental r2\n" +
            "        JOIN inventory i2 ON r2.inventory_id = i2.inventory_id\n" +
            "        JOIN film f2 ON i2.film_id = f2.film_id\n" +
            "        WHERE f2.rating = 'G'\n" +
            "    )\n" +
            ")\n" +
            "GROUP BY f.film_id, f.title\n" +
            "HAVING COUNT(r.rental_id) > 100\n" +
            "ORDER BY total_rentals DESC;", nativeQuery = true)
    public List<DTOL3E8> findFilmRentedThan100TimesButCustomerRentedFilmRatingG();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = rental_rate * 1.10 " +
            "WHERE film_id IN ( " +
            "    SELECT f.film_id FROM film f " +
            "    JOIN inventory i ON i.film_id = f.film_id " +
            "    JOIN rental r ON r.inventory_id = i.inventory_id " +
            "    GROUP BY f.film_id HAVING COUNT(r.rental_id) > 100)",
            nativeQuery = true)
    int updateRentalRateForPopularFilms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_duration = rental_duration * 1.05 " +
            "WHERE film_id IN ( " +
            "    SELECT f.film_id FROM film f " +
            "    JOIN inventory i ON i.film_id = f.film_id " +
            "    JOIN rental r ON r.inventory_id = i.inventory_id " +
            "    GROUP BY f.film_id HAVING COUNT(r.rental_id) > 5)",
            nativeQuery = true)
    int updateRentalDurationForFrequentFilms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = rental_rate * 1.2 " +
            "WHERE film_id IN ( " +
            "    SELECT f.film_id FROM film f " +
            "    JOIN film_category fc ON f.film_id = fc.film_id " +
            "    JOIN category c ON fc.category_id = c.category_id " +
            "    WHERE c.name = 'Action' AND f.release_year < 2005)",
            nativeQuery = true)
    int updateRentalRateForOldActionFilms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = CAST(LEAST(rental_rate * 1.05, 4.00) AS DECIMAL(5,2)) " +
            "WHERE film_id IN ( " +
            "    SELECT f.film_id FROM film f " +
            "    JOIN inventory i ON f.film_id = i.film_id " +
            "    JOIN rental r ON i.inventory_id = r.inventory_id " +
            "    GROUP BY f.film_id HAVING COUNT(DISTINCT r.customer_id) > 10)",
            nativeQuery = true)
    int updateRentalRateForFrequentCustomers();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = 3.50 " +
            "WHERE rating = 'PG-13' AND length > 120",
            nativeQuery = true)
    int updateRentalRateForPG13LongFilms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_duration = length " +
            "WHERE film_id IN ( " +
            "    SELECT DISTINCT film_id FROM film_category " +
            "    WHERE category_id = (SELECT category_id FROM category WHERE name = 'Sci-Fi')) " +
            "AND release_year = 2010",
            nativeQuery = true)
    int updateRentalDurationForSciFi2010Films();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = rental_rate * 0.85 " +
            "WHERE film_id IN ( " +
            "    SELECT f.film_id FROM film f " +
            "    JOIN film_category fc ON f.film_id = fc.film_id " +
            "    JOIN category c ON fc.category_id = c.category_id " +
            "    WHERE c.name = 'Comedy' AND f.release_year >= 2007)",
            nativeQuery = true)
    int updateRentalRateForComedyFilms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE film " +
            "SET rental_rate = 1.50 " +
            "WHERE rating = 'G' AND length < 60",
            nativeQuery = true)
    int updateRentalRateForShortGFilms();


}
