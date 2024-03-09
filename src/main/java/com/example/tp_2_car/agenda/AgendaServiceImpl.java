package com.example.tp_2_car.agenda;

import com.example.tp_2_car.personne.Personne;
import com.example.tp_2_car.personne.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Override
    @Transactional
    public void init() {
        Personne personne1 = personneRepository.findById(1L).orElse(null);
        Personne personne2 = personneRepository.findById(2L).orElse(null);
        agendaRepository.save(new Agenda( personne1,"mon agenda 1"));
        agendaRepository.save(new Agenda( personne2,"mon agenda 2"));
    }

    @Override
    public void ajouterAgenda( Personne personne,String nomAgenda) {
        // Recherche de la personne dans la base de données
        Personne existingPersonne = personneRepository.findById(personne.getId()).orElse(null);

        if (existingPersonne != null) {
            // La personne existe, vous pouvez créer l'agenda avec cette personne
            Agenda agenda = new Agenda( existingPersonne, nomAgenda);
            agendaRepository.save(agenda);
        } else {
            // Gérer le cas où la personne n'existe pas
        }
    }

    @Override
    public Iterable<Agenda> getAllAgenda() {
        return agendaRepository.findAll();
    }


    @Override
    public List<Agenda> getAgendasByPersonneId(Long id) {
        return agendaRepository.findByPersonneId(id);
    }
}
