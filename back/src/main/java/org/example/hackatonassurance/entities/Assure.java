package org.example.hackatonassurance.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Assure {
    private int numeroAssure;
    private int mensualite;
    private Bulletin bulletin;

    public Assure(int numeroAssure, int mensualite, Bulletin bulletin) {
        this.numeroAssure = numeroAssure;
        this.mensualite = mensualite;
        this.bulletin = bulletin;
    }

}
