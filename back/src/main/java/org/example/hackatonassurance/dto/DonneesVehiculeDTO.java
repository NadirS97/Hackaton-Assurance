package org.example.hackatonassurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonneesVehiculeDTO {
    private int distanceParourue;
    private LocalDateTime date;
}
