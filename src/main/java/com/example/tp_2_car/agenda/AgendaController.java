package com.example.tp_2_car.agenda;

import com.example.tp_2_car.personne.Personne;
import com.example.tp_2_car.personne.PersonneRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/listAgenda")
    public String listAgenda(Model model) {
        Iterable<Agenda> agendas = agendaService.getAllAgenda();
        model.addAttribute("agendas", agendas);
        return "agenda/list"; // Correction : retourne le nom de la vue Thymeleaf "agenda/list"
    }

    @PostMapping("/initAgenda")
    public String init() {
        agendaService.init();
        return "redirect:/agenda/listAgenda"; // Correction : redirige vers l'URL "/agenda/listAgenda"
    }

    @GetMapping("/showForm")
    public String showAgendaForm(Model model) {

        return "agenda/form"; // Retourne le nom de la vue Thymeleaf "agenda/form"
    }

    @PostMapping("/add")
    public String addAgenda(@RequestParam String nomAgenda, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            Personne personne = personneRepository.findById(userId).orElse(null);
            if (personne != null) {
                // Ajout de messages de log pour vérifier les données
                System.out.println("Personne trouvée : " + personne.getNom()); // Vous pouvez utiliser un logger au lieu de System.out.println
                System.out.println("Nom de l'agenda : " + nomAgenda);
                agendaService.ajouterAgenda(personne, nomAgenda);
            } else {
                // Ajouter un message de log si la personne n'est pas trouvée
                System.out.println("Personne non trouvée avec l'ID : " + userId);
            }
        } else {
            // Ajouter un message de log si l'ID de l'utilisateur n'est pas présent dans la session
            System.out.println("ID de l'utilisateur non trouvé dans la session");
        }
        String email = (String) session.getAttribute("userEmail");
        if (email != null) {
            System.out.println("Email récupéré de la session : " + email);
            // Continuer avec le reste du traitement
        } else {
            System.out.println("Email non trouvé dans la session.");
            // Gérer le cas où l'email n'est pas trouvé dans la session
        }

        return "redirect:/agenda/listAgenda";
    }
}