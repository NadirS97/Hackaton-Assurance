package org.example.hackatonassurance.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Accelerometre {
    private Double x;
    private Double y;
    private Double z;
    private LocalDate date;
}
