package com.example.tp_2_car.evenement;

import com.example.tp_2_car.agenda.Agenda;
import com.example.tp_2_car.agenda.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime; // Importez LocalTime
import java.util.Date; // Importez Date
import java.util.List;
import java.util.Optional;

@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Override
    @Transactional
    public void init() {
        Agenda agenda1 = agendaRepository.findById(1L).orElse(null);
        Agenda agenda2 = agendaRepository.findById(2L).orElse(null);

        // Créer une heure de début et de fin pour les événements de démonstration
        LocalTime startTime = LocalTime.of(9, 0); // Exemple : 09:00
        LocalTime endTime = LocalTime.of(11, 0); // Exemple : 11:00

        // Sauvegarder les événements avec les heures de début et de fin
        evenementRepository.save(new Evenement(agenda1, "mon evenement 1", new Date(), startTime, endTime));
        evenementRepository.save(new Evenement(agenda2, "mon evenement 2", new Date(), startTime, endTime));
    }


    @Override
    public void ajouterEvenement(Agenda agenda, String nomEvenement, Date dateEvenement, LocalTime startEvenement, LocalTime finishEvenement) {
        Agenda existingAgenda = agendaRepository.findById(agenda.getIdAgenda()).orElse(null);

        if (existingAgenda != null) {
            System.out.println("Ajout d'un nouvel evenement pour la agenda : " + existingAgenda.getNomAgenda());
            System.out.println("Nom de l'evenement : " + nomEvenement);

            Evenement evenement = new Evenement(existingAgenda, nomEvenement, dateEvenement, startEvenement, finishEvenement);
            evenementRepository.save(evenement);
        } else {
            System.out.println("La agenda n'existe pas dans la base de données");
        }
    }

    @Override
    public void deleteEvenement(Long idEvenement) {
        // Vérifiez d'abord si l'événement existe avec l'ID donné
        Optional<Evenement> evenementOptional = evenementRepository.findById(idEvenement);
        if (evenementOptional.isPresent()) {
            // Si l'événement existe, supprimez-le
            evenementRepository.delete(evenementOptional.get());
        } else {
            // Si l'événement n'existe pas, vous pouvez lancer une exception ou traiter cela d'une autre manière selon vos besoins
            throw new RuntimeException("Événement non trouvé avec l'ID: " + idEvenement);
        }
    }


    @Override
    public Iterable<Evenement> getAllEvenement() {
        return evenementRepository.findAll();
    }

    @Override
    public List<Agenda> getEvenementByAgendaIdAgenda(Long idAgenda) {
        return evenementRepository.findByAgendaIdAgenda(idAgenda);
    }
}
