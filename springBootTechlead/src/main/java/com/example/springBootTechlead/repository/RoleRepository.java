package com.example.springBootTechlead.repository;

import com.example.springBootTechlead.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from role where id = :id", nativeQuery = true)
    Role findRoleById(@RequestParam Integer id);
    @Query(value = "select * from role where name = :name", nativeQuery = true)
    Role findRoleByName(String name);
}
