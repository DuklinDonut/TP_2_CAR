package com.example.tp_2_car.evenement;

import com.example.tp_2_car.agenda.Agenda;
import com.example.tp_2_car.agenda.AgendaRepository;
import com.example.tp_2_car.personne.Personne;
import com.example.tp_2_car.personne.PersonneService;
import com.example.tp_2_car.personne.PersonneRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/evenement")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/listEvenement")
    public String listEvenement(Model model) {
        Iterable<Evenement> evenements = evenementService.getAllEvenement();
        model.addAttribute("evenements", evenements);
        return "evenement/list";
    }

    @PostMapping("/initEvenement")
    public String init() {
        evenementService.init();
        return "redirect:/agenda/home";
    }

    @GetMapping("/showForm")
    public String showEvenementForm(Model model) {
        return "evenement/form"; // Retourne le nom de la vue Thymeleaf "evenement/form"
    }

    @PostMapping("/add")
    public String addEvenement(@RequestParam("agendaId") Long agendaId,
                               @RequestParam String nomEvenement,
                               @RequestParam Date dateEvenement,
                               @RequestParam LocalTime startEvenement,
                               @RequestParam LocalTime finishEvenement,
                               HttpSession session) {
        //Long agendaId = (Long) session.getAttribute("agendaId");

        if (agendaId != null) {
            Agenda agenda = agendaRepository.findById(agendaId).orElse(null);
            if (agenda != null) {
                System.out.println("Agenda trouvé : " + agenda.getNomAgenda());
                System.out.println("Nom de l'événement : " + nomEvenement);
                evenementService.ajouterEvenement(agenda, nomEvenement, dateEvenement, startEvenement, finishEvenement);
            } else {
                System.out.println("Agenda non trouvé avec l'ID : " + agendaId);
            }
        } else {
            System.out.println("ID de l'agenda non trouvé dans la session");
        }
        return "redirect:/agenda/feuille";
    }

    @PostMapping("/agenda/feuille")
    public String addEvenement(@RequestParam("nomEvenement") String nomEvenement,
                               @RequestParam("dateEvenement") Date dateEvenement,
                               @RequestParam("startEvenement") LocalTime startEvenement,
                               @RequestParam("finishEvenement") LocalTime finishEvenement,
                               @RequestParam("IdAgenda") Long IdAgenda) {
        Agenda agenda = agendaRepository.findById(IdAgenda).orElse(null);
        evenementService.ajouterEvenement(agenda, nomEvenement, dateEvenement, startEvenement, finishEvenement);
        return "redirect:/agenda/feuille";
    }

    @GetMapping("/delete")
    public String deleteEvenement(@RequestParam("idEvenement") Long IdEvenement) {
        evenementService.deleteEvenement(IdEvenement);
        return "redirect:/agenda/feuille";
    }

    @GetMapping("/showEvenements")
    public String afficherEvenements(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            String nomUtilisateur = (String) session.getAttribute("userPrenom");
            model.addAttribute("nomUtilisateur", nomUtilisateur);
            List<Agenda> agendas = agendaRepository.findByPersonneId(userId);
            model.addAttribute("agendas", agendas);
        } else {
            // Gérer le cas où l'ID de l'utilisateur n'est pas trouvé dans la session
        }

        // Vérifiez s'il n'y a aucun agenda trouvé
        if (model.containsAttribute("agendas") && ((List<Agenda>) model.getAttribute("agendas")).isEmpty()) {
            model.addAttribute("noAgendas", true);
        }

        return "agenda/evenements";
    }




}



