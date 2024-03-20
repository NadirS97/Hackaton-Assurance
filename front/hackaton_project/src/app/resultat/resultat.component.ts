import { Component } from '@angular/core';

@Component({
  selector: 'app-resultat',
  templateUrl: './resultat.component.html',
  styleUrl: './resultat.component.scss'
})
export class ResultatComponent {
  assuranceAuto = {
    prix: 100,
    reduction: 10
  };

  statistiquesConduite = {
    kilometresParcourus: 400,
    freinagesBrusques: 20,
    accelerationsImprudentes: 20,
    pointsPerdus: 100,
    pointsRestants: 0
  };

  conseilsConduite = [
    "Respectez les limitations de vitesse.",
    "Anticipez les freinages et les changements de direction.",
    "Conduisez prudemment et soyez attentifs aux autres usagers de la route."
  ];


}
