package org.example.hackatonassurance.facade;

import org.example.hackatonassurance.entities.Accelerometre;

public interface FacadeUtilisateur {
    int calculerTauxReduction();
    void ajouterDonneesRecuesAccelerometre(Accelerometre accelerometre);
    int compterInfractions(int annee, int mois);
    void getDistanceParcourue(int distanceTotaleParcourue);
}
