package com.example.tp_2_car.personne;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agenda")

public class personneController {
    @Autowired
    private personneService service;
    @Autowired
    private personneRepository personneRepo;

    @GetMapping("/home")
    private String home(Model model) {
        Iterable<Personne> personnes = service.getAllPersonne();
        model.addAttribute("personnes", personnes);
        return "agenda/home";
    }
    @PostMapping("/init")
    public String init(){
        service.init();
        return "redirect:/agenda/home";
    }
    @PostMapping("/add")
    public String addPersonne(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nom,
            @RequestParam String prenom){
        service.ajouterPersonne(email, password, nom, prenom);
        return "redirect:/agenda/home";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Personne utilisateur = personneRepo.findByEmail(email);

        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            return "agenda/feuille";
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

}

