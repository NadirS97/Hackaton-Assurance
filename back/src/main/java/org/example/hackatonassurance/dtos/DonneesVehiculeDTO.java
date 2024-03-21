package org.example.hackatonassurance.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonneesVehiculeDTO {
    private int distanceParourue;
    private LocalDate date;
}
