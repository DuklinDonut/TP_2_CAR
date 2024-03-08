package com.example.tp_2_car.personne;

public interface personneService {

    void init();

    void ajouterPersonne(String email, String password, String nom, String prenom);


    Iterable<Personne> getAllPersonne();

}
