package org.example.hackatonassurance.controller;

import org.example.hackatonassurance.entities.Assure;
import org.example.hackatonassurance.facade.FacadeUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private final FacadeUtilisateur facadeUtilisateur;

    public Controller(FacadeUtilisateur facadeUtilisateur) {
        this.facadeUtilisateur = facadeUtilisateur;
    }

//    @PostMapping("/assures/creer")
//    public Assure creerAssure(@RequestBody  ) {
//        Capteur capteur = new Capteur();
//        if (request.getInfractions() != null) {
//            for (Integer infraction : request.getInfractions()) {
//                capteur.addInfraction(infraction);
//            }
//        }
//
//        Assure assure = new Assure(request.getNom(), request.getMensualite(), capteur);
//        assure.calculerMensualite();
//
//        return assure;
//    }
}
