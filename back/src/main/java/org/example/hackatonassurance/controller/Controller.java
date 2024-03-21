package org.example.hackatonassurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hackatonassurance.dtos.DonneesVehiculeDTO;
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
    public ResponseEntity<String> receptionDistanceParcourue(@RequestBody DonneesVehiculeDTO donneesVehicule){
        try {
            facadeUtilisateur.getDistanceParcourue(donneesVehicule);
            return ResponseEntity.ok("Distance parcourue reçue avec succès"+donneesVehicule.getDistanceParourue());
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la reception de la distance parcourue : " + e.getMessage());
        }
    }

    @GetMapping("/informationsScore")
    public ResponseEntity<String> getInfractionsDuMois() {
        try {
            LocalDate dateActuelle = LocalDate.now();
            Map<String, Integer> infractionsDuMois = facadeUtilisateur.compterInfractions(dateActuelle.getYear(), dateActuelle.getMonthValue());
            int distanceTotaleDuMois = facadeUtilisateur.getDistanceParcourueDurantleMois(dateActuelle.getYear(), dateActuelle.getMonthValue());
            int tauxReduction = facadeUtilisateur.calculerTauxReduction();
            int scoreDuMois = facadeUtilisateur.getScore();

            Map<String, Integer> informations = new HashMap<>();
            informations.put("infractions", infractionsDuMois.get("nbTotalInfractions"));
            informations.put("accelerationForte", infractionsDuMois.get("accelerationForte"));
            informations.put("freinageBrusque", infractionsDuMois.get("freinageBrusque"));
            informations.put("distanceTotal", distanceTotaleDuMois);
            informations.put("tauxReduction", tauxReduction);
            informations.put("score", scoreDuMois);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInformations = objectMapper.writeValueAsString(informations);

            return ResponseEntity.ok(jsonInformations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des données : " + e.getMessage());
        }
    }

}
