package org.example.hackatonassurance.facade;

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

    public FacadeUtilisateurImpl() {
        this.assure = new Assure(0,0, new Bulletin());
        this.donneesRecuesAccelerometre = new ArrayList<>();
    }

    @Override
    public Assure getAssure() {
        return assure;
    }

    @Override
    public void ajouterDonneesRecuesAccelerometre(Accelerometre accelerometre) {
        this.donneesRecuesAccelerometre.add(accelerometre);
    }

    @Override
    public void compterInfractions(int annee, int mois) {
        int cptInfractions = 0;
        for (Accelerometre accelerometre : donneesRecuesAccelerometre) {
            if (accelerometre.getDate().getYear() == annee &&   accelerometre.getDate().getMonthValue() == mois) {
                cptInfractions++;
            }
        }
        assure.getBulletin().setInfractions(cptInfractions);
    }

    public void scoreUpdate(){
        int score = assure.getBulletin().getScore();
        int infractions = assure.getBulletin().getInfractions();
        if (infractions > 0) {
            score -= infractions;
        }
        assure.getBulletin().setScore(score);
    }

    @Override
    public int calculerMensualite() {
        Bulletin bulletin = assure.getBulletin();
        scoreUpdate();
        int scoreAssure = bulletin.getScore();
        if (scoreAssure < 1) {
            assure.setMensualite(100);
        } else if (scoreAssure >= 1 && scoreAssure <= 200) {
            assure.setMensualite(95);
        } else if (scoreAssure > 200 && scoreAssure <= 400) {
                assure.setMensualite(90);
        } else if (scoreAssure > 400 && scoreAssure <= 600) {
            assure.setMensualite(85);
        } else if (scoreAssure > 600 && scoreAssure <= 800) {
            assure.setMensualite(80);
        } else if (scoreAssure > 800) {
            assure.setMensualite(75);
        }
        return assure.getMensualite();
    }
}
