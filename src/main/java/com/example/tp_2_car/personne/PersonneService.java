package com.example.tp_2_car.personne;

public interface PersonneService {

    void init();

    void ajouterPersonne(String email, String password, String nom, String prenom);


    Iterable<Personne> getAllPersonne();

}
