package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E6;
import com.example.springBootTechlead.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

    @Query(value = "SELECT s.store_id , SUM(amount) AS revenue\n" +
            "FROM store s LEFT JOIN customer c ON s.store_id = c.store_id\n" +
            "\t\tJOIN payment p ON c.customer_id = p.customer_id\n" +
            "WHERE YEAR(payment_date) = 2021\n" +
            "GROUP BY s.store_id;", nativeQuery = true)
    public List<DTOL1E6> findRevenue2021();

}
