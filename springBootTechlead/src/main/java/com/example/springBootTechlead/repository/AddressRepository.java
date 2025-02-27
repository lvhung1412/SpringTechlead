package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE address " +
            "SET address = CONCAT(address, ' samecity') " +
            "WHERE address_id IN ( " +
            "    SELECT c.address_id FROM customer c " +
            "    JOIN address a ON a.address_id = c.address_id " +
            "    WHERE EXISTS ( " +
            "        SELECT c1.customer_id FROM customer c1 " +
            "        JOIN address a1 ON a1.address_id = c1.address_id " +
            "        WHERE c.last_name = c1.last_name " +
            "        AND c.customer_id <> c1.customer_id " +
            "        AND a.city_id = a1.city_id))",
            nativeQuery = true)
    int updateAddressForSameCityCustomers();

}
