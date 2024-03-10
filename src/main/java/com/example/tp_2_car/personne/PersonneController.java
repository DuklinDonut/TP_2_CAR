package com.example.tp_2_car.personne;

import com.example.tp_2_car.agenda.Agenda;
import com.example.tp_2_car.agenda.AgendaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agenda")

public class PersonneController {

    private AgendaService agendaService;
    @Autowired
    private PersonneService service;
    @Autowired
    private PersonneRepository personneRepo;

    @GetMapping("/home")
    private String home(Model model) {
        Iterable<Personne> personnes = service.getAllPersonne();
        model.addAttribute("personnes", personnes);
        return "agenda/home";
    }

    @PostMapping("/initPersonne")
    public String init() {
        service.init();
        return "redirect:/agenda/home";
    }

    @PostMapping("/addPersonne")
    public String addPersonne(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nom,
            @RequestParam String prenom) {
        service.ajouterPersonne(email, password, nom, prenom);
        return "redirect:/agenda/home";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Personne utilisateur = personneRepo.findByEmail(email);

        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            session.setAttribute("userId", utilisateur.getId());
            session.setAttribute("userEmail", utilisateur.getEmail());
            session.setAttribute("userPrenom", utilisateur.getPrenom());
            return "redirect:/agenda/feuille";
        } else {
            return "agenda/loginError";
        }
    }



    @GetMapping("/logout") //  GetMapping
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/agenda/home";
    }

    @GetMapping("/feuille")
    public String feuille(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            Optional<Personne> optionalPersonne = personneRepo.findById(userId);

            if (optionalPersonne.isPresent()) {
                Personne personne = optionalPersonne.get();
                List<Agenda> agendas = personne.getAgendas();
                model.addAttribute("agendas", agendas);
                model.addAttribute("prenom", personne.getPrenom());
            } else {
                // Handle case where user is not found in the database
                System.out.println("Personne non trouvée dans la base de données");
                return "redirect:/agenda/home";
            }
        } else {
            // Handle case where user is not logged in
            System.out.println("Personne non connectée");
            return "redirect:/agenda/home";
        }

        return "agenda/feuille";
    }








}
