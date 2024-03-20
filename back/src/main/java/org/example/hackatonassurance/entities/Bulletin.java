package org.example.hackatonassurance.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bulletin {
    private int infractions;
    private int score = 1000;
}
