package com.example.tp_2_car.agenda;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {
    @Query("SELECT a FROM Agenda a WHERE a.nomAgenda = :nomAgenda")
    Agenda findByNomAgenda(@Param("nomAgenda") String nomAgenda);

    List<Agenda> findByPersonneId(Long id);
}
