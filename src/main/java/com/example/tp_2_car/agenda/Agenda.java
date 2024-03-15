package com.example.tp_2_car.agenda;

import com.example.tp_2_car.evenement.Evenement;
import com.example.tp_2_car.personne.Personne;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;
    private String nomAgenda;
    @ManyToOne
    private Personne personne;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Evenement> evenements;




    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }


    public String getNomAgenda() {
        return nomAgenda;
    }

    public void setNomAgenda(String nomAgenda) {
        this.nomAgenda = nomAgenda;
    }


    public Agenda() {}

    // Constructeur avec le nom de l'agenda et la personne associée
    public Agenda(Personne personne,String nomAgenda) {
        this.nomAgenda = nomAgenda;
        this.personne = personne;
    }


    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
        if (personne != null) {
            personne.getAgendas().add(this);
        }
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }
}