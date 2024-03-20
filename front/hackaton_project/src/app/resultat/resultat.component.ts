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
    prix: 120,
    reduction: 25
  };

  statistiquesConduite = {
    kilometresParcourus: 0,
    freinagesBrusques: 20,
    accelerationsImprudentes: 20,
    pointsPerdus: 0,
    pointsRestants: 1000
  };

  conseilsConduite = [
    "Respectez les limitations de vitesse.",
    "Anticipez les freinages et les changements de direction.",
    "Conduisez prudemment et soyez attentifs aux autres usagers de la route."
  ];

  accelerationData: any = { x: 0, y: 0, z: 0 };

  constructor(private accelerometerService: AccelerometerService,private distanceTrackerService: GpsService) { }

  ngOnInit(): void {
    const accelerationDataInit: any = { x: 0, y: 9, z: 4.5 };
    let isInfraction=false;
    this.accelerometerService.getAcceleration((data: any) => {
      this.accelerationData = data.accelerationIncludingGravity;

      console.log(this.accelerationData)
      if ((this.accelerationData.x-accelerationDataInit.x) >=3 || this.accelerationData.y<9 || this.accelerationData.z<4.5){
        isInfraction = true;
        console.log('acceleration')
      }
      if (this.accelerationData.x < -1.5 || this.accelerationData.y< -1.5 || this.accelerationData.z<-1.5){
        isInfraction = true;
        console.log('freins brusque')
      }
      if (isInfraction){
        console.log('post');
        isInfraction=false

      }
      this.distanceTrackerService.startTracking();
      this.distanceTrackerService.distanceSubject.subscribe(distance => {
        this.statistiquesConduite.kilometresParcourus = distance;
      });
    });
  }
}
