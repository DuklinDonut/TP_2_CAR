package com.example.tp_2_car.evenement;

import com.example.tp_2_car.agenda.Agenda;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface EvenementService {

    void init();

    void ajouterEvenement(Agenda agenda, String nomEvenement, Date dateEvenement, LocalTime startEvenement, LocalTime finishEvenement);

    void deleteEvenement(Long idEvenement);

    Iterable<Evenement> getAllEvenement();


    List<Agenda> getEvenementByAgendaIdAgenda(Long idAgenda);
}
