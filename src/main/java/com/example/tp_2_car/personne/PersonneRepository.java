package com.example.tp_2_car.personne;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.OptionalLong;

public interface PersonneRepository extends CrudRepository <Personne, Long> {
    @Query("SELECT p FROM Personne p WHERE p.email = :email")
    Personne findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Personne p WHERE p.id = :id")
    Optional<Personne> findById(@Param("id") Long id);

}
