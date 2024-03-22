package com.example.tp_2_car.personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneServiceImpl implements PersonneService {
    @Autowired
    private PersonneRepository repo;

    @Override
    public void init() {
        repo.save(new Personne("f@gmail.com", "f", "fatima", "fati"));
        repo.save(new Personne("z@gmail.com", "z", "fatima2", "fati2"));
    }

    @Override
    public void ajouterPersonne(String email, String password, String nom, String prenom) {
        repo.save(new Personne(email, password, nom, prenom));
    }

    @Override
    public Iterable<Personne> getAllPersonne() {
        return repo.findAll();
    }


}
