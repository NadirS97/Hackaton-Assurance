package org.example.hackatonassurance.facade;

import org.example.hackatonassurance.dtos.DonneesVehiculeDTO;
import org.example.hackatonassurance.entities.Accelerometre;
import org.example.hackatonassurance.entities.Assure;
import org.example.hackatonassurance.entities.Bulletin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacadeUtilisateurImpl implements FacadeUtilisateur {

    private Assure assure;
    private final List<Accelerometre> donneesRecuesAccelerometre;
    private final List<DonneesVehiculeDTO> listeDistancesParcourues;

    public FacadeUtilisateurImpl() {
        this.assure = new Assure(0,0, new Bulletin());
        this.donneesRecuesAccelerometre = new ArrayList<>();
        this.listeDistancesParcourues = new ArrayList<>();
    }

    @Override
    public void ajouterDonneesRecuesAccelerometre(Accelerometre accelerometre) {
        this.donneesRecuesAccelerometre.add(accelerometre);
    }

    @Override
    public int compterInfractions(int annee, int mois) {
        int cptInfractions = 0;
        for (Accelerometre accelerometre : donneesRecuesAccelerometre) {
            if (accelerometre.getDate().getYear() == annee &&   accelerometre.getDate().getMonthValue() == mois) {
                cptInfractions++;
            }
        }
        this.assure.getBulletin().setInfractions(cptInfractions);
        return cptInfractions;
    }

    public void scoreUpdate(){
        int score = 1000;
        int infractions = this.assure.getBulletin().getInfractions();
        int distanceTotale = this.assure.getBulletin().getDistanceTotaleParcourue();
        if (infractions > 0) {
            score -= infractions;
        }
        if (distanceTotale >= 2) {
            score -= (int) Math.floor((distanceTotale/2));
        }
        this.assure.getBulletin().setScore(score);
    }

    @Override
    public int calculerTauxReduction() {
        Bulletin bulletin = assure.getBulletin();
        scoreUpdate();
        int scoreAssure = bulletin.getScore();
        if (scoreAssure < 1) {
            this.assure.setMensualite(0);
        } else if (scoreAssure <= 200) {
            this.assure.setMensualite(5);
        } else if (scoreAssure <= 400) {
            this.assure.setMensualite(10);
        } else if (scoreAssure <= 600) {
            this.assure.setMensualite(15);
        } else if (scoreAssure <= 800) {
            this.assure.setMensualite(20);
        } else {
            this.assure.setMensualite(25);
        }
        return this.assure.getMensualite();
    }

    @Override
    public void getDistanceParcourue(DonneesVehiculeDTO donneesVehicule){
        listeDistancesParcourues.add(donneesVehicule);
    }

    @Override
    public int getScore(){
        return this.assure.getBulletin().getScore();
    }

    @Override
    public int getDistanceParcourueDurantleMois(int annee, int mois){
        int distanceParcourueDurantleMois = 0;
        for (DonneesVehiculeDTO donneesVehicule : listeDistancesParcourues) {
            if (donneesVehicule.getDate().getYear() == annee &&   donneesVehicule.getDate().getMonthValue() == mois) {
                distanceParcourueDurantleMois += donneesVehicule.getDistanceParourue();
            }
        }
        this.assure.getBulletin().setDistanceTotaleParcourue(distanceParcourueDurantleMois);
        calculerTauxReduction();
        return distanceParcourueDurantleMois;
    }
}
