package org.example.hackatonassurance.facade;

import org.example.hackatonassurance.entities.Accelerometre;
import org.example.hackatonassurance.entities.Assure;

public interface FacadeUtilisateur {
    int calculerMensualite();
    void ajouterDonneesRecuesAccelerometre(Accelerometre accelerometre);
    Assure getAssure();
    void compterInfractions(int annee, int mois);

}
