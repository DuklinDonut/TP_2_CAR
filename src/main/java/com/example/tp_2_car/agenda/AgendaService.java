package com.example.tp_2_car.agenda;

import com.example.tp_2_car.personne.Personne;

import java.util.List;

public interface AgendaService {

    void init();


    void ajouterAgenda(Personne personneId, String nomAgenda);

    Iterable<Agenda> getAllAgenda();

    List<Agenda> getAgendasByPersonneId(Long Id);
}
