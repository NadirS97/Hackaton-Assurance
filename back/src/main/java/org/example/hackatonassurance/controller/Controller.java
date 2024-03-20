package org.example.hackatonassurance.controller;

import org.example.hackatonassurance.entities.Accelerometre;
import org.example.hackatonassurance.facade.FacadeUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private final FacadeUtilisateur facadeUtilisateur;

    public Controller(FacadeUtilisateur facadeUtilisateur) {
        this.facadeUtilisateur = facadeUtilisateur;
    }

    @PostMapping("/accelerometre")
    public ResponseEntity<String> receptionAccelerometreData(@RequestBody Accelerometre accelerometre){
        try {
            facadeUtilisateur.ajouterDonneesRecuesAccelerometre(accelerometre);
            return ResponseEntity.ok("Données de l'accelerometre reçues avec succès");
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la reception des données de l'accelerometre : " + e.getMessage());
        }
    }

    @PostMapping("/distanceParcourue")
    public ResponseEntity<String> receptionDistanceParcourue(@RequestBody String distanceParcourue){
        try {

            return ResponseEntity.ok("Distance parcourue reçue avec succès");
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la reception de la distance parcourue : " + e.getMessage());
        }
    }

    @GetMapping("/infractions")
    public ResponseEntity<String> recupMensualite() {
        try {
            LocalDate dateActuelle = LocalDate.now();
            facadeUtilisateur.compterInfractions(dateActuelle.getYear(), dateActuelle.getMonthValue());
            int mensualite = facadeUtilisateur.calculerMensualite();
            return ResponseEntity.ok(String.valueOf(mensualite));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération de la mensualité : " + e.getMessage());
        }

    }


}
