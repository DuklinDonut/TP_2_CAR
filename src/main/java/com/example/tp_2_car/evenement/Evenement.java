package com.example.tp_2_car.evenement;

import com.example.tp_2_car.agenda.Agenda;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;

@Entity
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    private String nomEvenement;
    private Date dateEvenement;

    @DateTimeFormat(pattern = "HH:mm") // Annotation de formatage pour l'heure de début
    private LocalTime startEvenement;

    @DateTimeFormat(pattern = "HH:mm") // Annotation de formatage pour l'heure de fin
    private LocalTime finishEvenement;

    @ManyToOne
    private Agenda agenda;




    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }


    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public LocalTime getStartEvenement() {
        return startEvenement;
    }

    public void setStartEvenement(LocalTime startEvenement) {
        this.startEvenement = startEvenement;
    }

    public LocalTime getFinishEvenement() {
        return finishEvenement;
    }

    public void setFinishEvenement(LocalTime finishEvenement) {
        this.finishEvenement = finishEvenement;
    }

    public Evenement() {

    }


    // Constructeur avec le nom de l'Evenement et l'agenda associée
    public Evenement(Agenda agenda, String nomEvenement, Date dateEvenement, LocalTime startEvenement, LocalTime finishEvenement) {
        this.nomEvenement = nomEvenement;
        this.agenda = agenda;
        this.dateEvenement = dateEvenement;
        this.startEvenement= startEvenement;
        this.finishEvenement= finishEvenement;
    }


    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
        if (agenda != null) {
            agenda.getEvenements().add(this);
        }
    }
}