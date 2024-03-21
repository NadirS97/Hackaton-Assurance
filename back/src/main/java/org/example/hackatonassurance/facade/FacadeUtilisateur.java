package org.example.hackatonassurance.facade;

import org.example.hackatonassurance.dtos.DonneesVehiculeDTO;
import org.example.hackatonassurance.entities.Accelerometre;

import java.util.Map;

public interface FacadeUtilisateur {
    int calculerTauxReduction();
    void ajouterDonneesRecuesAccelerometre(Accelerometre accelerometre);
    Map<String, Integer> compterInfractions(int annee, int mois);
    void getDistanceParcourue(DonneesVehiculeDTO DonneesVehicule);
    int getScore();
    int getDistanceParcourueDurantleMois(int annee, int mois);
}
