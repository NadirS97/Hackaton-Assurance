package org.example.hackatonassurance.entities;

import lombok.*;
import org.example.hackatonassurance.dto.CapteurDonneesDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Assure {
    private int numeroAssure;
    private int mensualite;
    private Bulletin bulletin;
}
