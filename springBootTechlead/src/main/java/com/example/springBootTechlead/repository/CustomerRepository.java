package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E5;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E1;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E2;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E5;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E9;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E3;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E4;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E7;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E9;
import com.example.springBootTechlead.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT distinct c.first_name, c.last_name, a.address\n" +
            "FROM address a JOIN customer c ON a.address_id = c.address_id\n" +
            "\tJOIN rental r ON r.customer_id = c.customer_id\n" +
            "WHERE month(r.rental_date) = 1 AND YEAR(r.rental_date) = 2022;", nativeQuery = true)
    public List<DTOL1E5> findNameAndAddressWhoRentedFilmOnJanuary2022();

    @Query(value = "SELECT c.customer_id, c.first_name, c.last_name, SUM(amount) AS total_revenue\n" +
            "FROM customer c JOIN payment p ON c.customer_id = p.customer_id\n" +
            "GROUP BY c.customer_id, c.first_name, c.last_name\n" +
            "ORDER BY total_revenue desc\n" +
            "LIMIT 10;", nativeQuery = true)
    public List<DTOL2E1> findTop10HavingMostRevenue();

    @Query(value = "SELECT c.customer_id, c.first_name, c.last_name, c.email\n" +
            "FROM customer c JOIN rental r ON c.customer_id = r.customer_id\n" +
            "\t\tJOIN inventory i ON r.inventory_id = i.inventory_id\n" +
            "\t\tJOIN film f ON f.film_id = i.film_id\n" +
            "\t\tJOIN film_category fc ON fc.film_id = f.film_id\n" +
            "GROUP BY c.first_name, c.last_name, c.email\n" +
            "HAVING  COUNT(distinct fc.category_id) = (SELECT COUNT(category_id) FROM category);", nativeQuery = true)
    public List<DTOL2E2> findCustomerRentedFilmInAllCategory();

    @Query(value = "SELECT c.customer_id, c.first_name, c.last_name, f.title, COUNT(f.film_id) AS 'count'\n" +
            "FROM customer c\n" +
            "\tJOIN rental r ON r.customer_id = c.customer_id\n" +
            "\tJOIN inventory ON inventory.inventory_id = r.inventory_id\n" +
            "\tJOIN film_category fc ON fc.film_id = inventory.film_id\n" +
            "\tJOIN film f ON f.film_id = fc.film_id\n" +
            "GROUP BY c.customer_id, f.film_id\n" +
            "HAVING COUNT(f.film_id) > 1\n" +
            "ORDER BY c.customer_id ASC;", nativeQuery = true)
    public List<DTOL2E5> findCustomerRentedSameFilmMoreThanOnce();

    @Query(value = "SELECT customer.first_name, customer.last_name\n" +
            "FROM customer\n" +
            "\tJOIN rental ON rental.customer_id = customer.customer_id\n" +
            "\tJOIN inventory ON inventory.inventory_id = rental.inventory_id\n" +
            "\tJOIN film ON film.film_id = inventory.film_id\n" +
            "\tJOIN film_category on film_category.film_id = film.film_id\n" +
            "\tJOIN category on category.category_id = film_category.category_id\n" +
            "WHERE not exists (\n" +
            "\tSELECT DISTINCT fc1.category_id\n" +
            "\tFROM rental r1\n" +
            "\t\tJOIN customer c1 on c1.customer_id = r1.customer_id\n" +
            "\t\tJOIN inventory i1 ON i1.inventory_id = r1.inventory_id\n" +
            "\t\tJOIN film f1 on f1.film_id = i1.film_id\n" +
            "\t\tJOIN film_category fc1 on fc1.film_id = f1.film_id\n" +
            "\tWHERE c1.customer_id = customer.customer_id\n" +
            "\t\tand fc1.category_id = film_category.category_id\n" +
            "\t\tand r1.return_date < rental.return_date\n" +
            ")\n" +
            "GROUP BY customer.customer_id;", nativeQuery = true)
    public List<DTOL2E9> findCustomersRentedFilmThatNeverRentedBefore();

    @Query(value = "SELECT \n" +
            "    c.first_name, \n" +
            "    c.last_name, \n" +
            "    COUNT(r.rental_id) AS num_films_rented, \n" +
            "    SUM(p.amount) AS total_rental_fee\n" +
            "FROM customer c\n" +
            "\tJOIN rental r ON c.customer_id = r.customer_id\n" +
            "\tJOIN payment p ON r.rental_id = p.rental_id\n" +
            "GROUP BY c.customer_id\n" +
            "HAVING COUNT(r.rental_id) > 10;", nativeQuery = true)
    public List<DTOL3E3> findCustomerRentedThan10Films();

    @Query(value = "SELECT c.first_name, c.last_name, COUNT(f.film_id) AS 'number_of_film', SUM(p.amount) AS 'rental_fee'\n" +
            "FROM customer c\n" +
            "\tJOIN rental r ON r.customer_id = c.customer_id\n" +
            "\tJOIN inventory i ON i.inventory_id = r.inventory_id\n" +
            "\tJOIN film f ON f.film_id = i.film_id\n" +
            "\tJOIN film_category fc ON fc.film_id = f.film_id\n" +
            "\tJOIN category ca ON ca.category_id = fc.category_id\n" +
            "\tJOIN payment p ON r.rental_id = p.rental_id\n" +
            "GROUP BY c.customer_id\n" +
            "HAVING COUNT(distinct fc.category_id) = (SELECT COUNT(category_id) FROM category);", nativeQuery = true)
    public List<DTOL3E4> findCustomerRentedEveryFilm();

    @Query(value = "SELECT \n" +
            "    c.first_name, \n" +
            "    c.last_name, \n" +
            "    fc.category_id, \n" +
            "    COUNT(DISTINCT r.rental_id) AS films_rented\n" +
            "FROM customer c\n" +
            "\tJOIN rental r ON c.customer_id = r.customer_id\n" +
            "\tJOIN inventory i ON r.inventory_id = i.inventory_id\n" +
            "\tJOIN film f ON i.film_id = f.film_id\n" +
            "\tJOIN film_category fc ON f.film_id = fc.film_id\n" +
            "WHERE c.customer_id IN (\n" +
            "      SELECT r2.customer_id\n" +
            "      FROM rental r2\n" +
            "      JOIN inventory i2 ON r2.inventory_id = i2.inventory_id\n" +
            "      JOIN film_category fc2 ON i2.film_id = fc2.film_id\n" +
            "      GROUP BY r2.customer_id\n" +
            "      HAVING COUNT(DISTINCT fc2.category_id) = (SELECT COUNT(*) FROM category)\n" +
            ")\n" +
            "GROUP BY c.customer_id, fc.category_id;", nativeQuery = true)
    public List<DTOL3E7> findCustomerRentedAtLeast1FilmFromEachCategory();

    @Query(value = "SELECT customer.first_name, customer.last_name\n" +
            "FROM customer\n" +
            "\tJOIN rental ON rental.customer_id = customer.customer_id\n" +
            "\tJOIN inventory ON inventory.inventory_id = rental.inventory_id\n" +
            "\tJOIN film ON film.film_id = inventory.film_id\n" +
            "\tJOIN film_category ON film_category.film_id = film.film_id\n" +
            "\tJOIN category ON category.category_id = film_category.category_id\n" +
            "WHERE NOT EXISTS (\n" +
            "    -- Kiểm tra xem khách hàng đã từng thuê phim nào có thời lượng > 180 phút hay chưa\n" +
            "    SELECT 1\n" +
            "    FROM rental r2\n" +
            "\t    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id\n" +
            "\t    JOIN film f2 ON i2.film_id = f2.film_id\n" +
            "    WHERE f2.length > 180\n" +
            "    AND r2.customer_id = customer.customer_id\n" +
            ") \n" +
            "AND NOT EXISTS (\n" +
            "    -- Kiểm tra xem khách hàng đã từng thuê phim thuộc danh mục hiện tại trước đó hay chưa\n" +
            "    SELECT 1\n" +
            "    FROM rental r1\n" +
            "\t    JOIN inventory i1 ON i1.inventory_id = r1.inventory_id\n" +
            "\t    JOIN film f1 ON f1.film_id = i1.film_id\n" +
            "\t    JOIN film_category fc1 ON fc1.film_id = f1.film_id\n" +
            "    WHERE r1.customer_id = customer.customer_id\n" +
            "    AND fc1.category_id = film_category.category_id\n" +
            "    AND r1.return_date < rental.return_date -- Chỉ xét các lần thuê trước đó\n" +
            ")\n" +
            "GROUP BY customer.customer_id;", nativeQuery = true)
    public List<DTOL3E9> findCustomerRentedFilmThatNeverRentedBeforeAndNeverThan3Hours();

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer " +
            "SET email = CONCAT(email, 'horrorlover') " +
            "WHERE EXISTS ( " +
            "    SELECT 1 FROM rental r " +
            "    JOIN inventory i ON r.inventory_id = i.inventory_id " +
            "    JOIN film f ON i.film_id = f.film_id " +
            "    JOIN film_category fc ON f.film_id = fc.film_id " +
            "    JOIN category c ON fc.category_id = c.category_id " +
            "    WHERE c.name = 'Horror' " +
            "    AND r.customer_id = customer.customer_id " +
            "    AND r.rental_date BETWEEN '2022-10-01' AND '2022-10-31')",
            nativeQuery = true)
    int updateCustomerEmailForHorrorRentals();

}
