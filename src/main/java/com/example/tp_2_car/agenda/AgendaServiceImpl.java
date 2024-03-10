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
    public void ajouterAgenda(Personne personne, String nomAgenda) {

        Personne existingPersonne = personneRepository.findById(personne.getId()).orElse(null);

        if (existingPersonne != null) {

            System.out.println("Ajout d'un nouvel agenda pour la personne : " + existingPersonne.getNom());
            System.out.println("Nom de l'agenda : " + nomAgenda);

            // création de l'agenda et sauvegarde dans la base de données
            Agenda agenda = new Agenda(existingPersonne, nomAgenda);
            agendaRepository.save(agenda);
        } else {
            // gérer le cas où la personne n'existe pas
            System.out.println("La personne n'existe pas dans la base de données");
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
