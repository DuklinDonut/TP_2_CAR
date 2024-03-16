package com.example.tp_2_car.evenement;

import com.example.tp_2_car.evenement.Evenement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface EvenementRepository extends CrudRepository<Evenement, Long> {
    //@Query("SELECT a FROM Evenement a WHERE a.nomEvenement = :nomEvenement AND a.startEvenement = :startEvenement AND a.finishEvenement = :finishEvenement")
    List<Evenement> findByAgendaIdAgenda(Long idAgenda);

}
