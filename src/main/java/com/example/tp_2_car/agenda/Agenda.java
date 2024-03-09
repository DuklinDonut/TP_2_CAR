package com.example.tp_2_car.agenda;

import com.example.tp_2_car.personne.Personne;
import jakarta.persistence.*;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;
    private String nomAgenda;
    @ManyToOne
    private Personne personne;




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
}