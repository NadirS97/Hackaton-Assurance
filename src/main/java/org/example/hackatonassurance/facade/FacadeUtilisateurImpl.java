package org.example.hackatonassurance.facade;

import org.example.hackatonassurance.entities.Assure;
import org.example.hackatonassurance.entities.Bulletin;
import org.springframework.stereotype.Service;

@Service
public class FacadeUtilisateurImpl implements FacadeUtilisateur {

    @Override
    public Assure calculerMensualite(Assure assure) {
        Bulletin bulletin = assure.getBulletin();
        bulletin.calcul();
        int scoreAssure = bulletin.getScore();
        if (scoreAssure < 11) {
            assure.setMensualite(100);
        } else if (scoreAssure < 91) {
            assure.setMensualite(90);
        } else {
            assure.setMensualite(60);
        }
        return assure;
    }
}
