import {Component, OnInit} from '@angular/core';
import {AccelerometerService} from "../shared/services/accelerometer/accelerometer.service";

@Component({
  selector: 'app-resultat',
  templateUrl: './resultat.component.html',
  styleUrl: './resultat.component.scss'
})
export class ResultatComponent implements OnInit{
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

  accelerationData: any = { x: 0, y: 0, z: 0 };

  constructor(private accelerometerService: AccelerometerService) { }

  ngOnInit(): void {
    this.accelerometerService.getAcceleration((data: any) => {
      this.accelerationData = data.accelerationIncludingGravity;
    });
  }


}
