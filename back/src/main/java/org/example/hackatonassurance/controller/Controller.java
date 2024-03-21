package org.example.hackatonassurance.controller;

import org.example.hackatonassurance.dto.DonneesVehiculeDTO;
import org.example.hackatonassurance.entities.Accelerometre;
import org.example.hackatonassurance.facade.FacadeUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<String> receptionDistanceParcourue(@RequestBody DonneesVehiculeDTO DonneesVehicule){
        try {
            facadeUtilisateur.getDistanceParcourue(DonneesVehicule);
            return ResponseEntity.ok("Distance parcourue reçue avec succès");
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la reception de la distance parcourue : " + e.getMessage());
        }
    }

    @GetMapping("/informationsScore")
    public ResponseEntity<String> getInfractionsDuMois() {
        try {
            LocalDate dateActuelle = LocalDate.now();
            int infractionsDuMois = facadeUtilisateur.compterInfractions(dateActuelle.getYear(), dateActuelle.getMonthValue());
            int distanceTotaleDuMois = facadeUtilisateur.getDistanceParcourueDurantleMois(dateActuelle.getYear(), dateActuelle.getMonthValue());
            int tauxReduction = facadeUtilisateur.calculerTauxReduction();
            int scoreDuMois = facadeUtilisateur.getScore();

            Map<String, Integer> informations = new HashMap<>();
            informations.put("infractions", infractionsDuMois);
            informations.put("distanceTotal", distanceTotaleDuMois);
            informations.put("tauxReduction", tauxReduction);
            informations.put("score", scoreDuMois);

            return ResponseEntity.ok(String.valueOf(informations));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des données : " + e.getMessage());
        }
    }

}
