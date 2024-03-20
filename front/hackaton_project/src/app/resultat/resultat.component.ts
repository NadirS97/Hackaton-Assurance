import {Component, OnInit} from '@angular/core';
import {AccelerometerService} from "../shared/services/accelerometer/accelerometer.service";
import {GpsService} from "../shared/services/gps/gps.service";

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

  distance: number = 0;

  accelerationData: any = { x: 0, y: 0, z: 0 };

  constructor(private accelerometerService: AccelerometerService,private distanceTrackerService: GpsService) { }

  ngOnInit(): void {
    this.accelerometerService.getAcceleration((data: any) => {
      this.accelerationData = data.accelerationIncludingGravity;

      this.distanceTrackerService.startTracking();
      this.distanceTrackerService.distanceSubject.subscribe(distance => {
        this.distance = distance;
      });
    });
  }


}
