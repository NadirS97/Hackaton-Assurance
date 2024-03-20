package org.example.hackatonassurance.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Bulletin {
    private int id;
    private List<Integer> infractions;
    private int score = 100;

    public void calcul() {
        int sub = 0;
        for (int infraction : this.infractions) {
            sub += infraction;
        }
        if (sub > 100) {
            sub = 100;
        }
        this.score -= sub;
    }
}
