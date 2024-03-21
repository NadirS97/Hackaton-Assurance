import {Component, OnInit} from '@angular/core';
import {AccelerometerService} from "../shared/services/accelerometer/accelerometer.service";
import {GpsService} from "../shared/services/gps/gps.service";
import {HackatonService} from "../shared/services/hackaton/hackaton.service";

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

  accelerationData: any = { x: 0, y: 9, z: 4.5 };

  constructor(private accelerometerService: AccelerometerService,private distanceTrackerService: GpsService, private hackatonService :HackatonService) { }

  ngOnInit(): void {
    const date = new Date();
    let todaysDate : string = `${date.getFullYear()}-${("0" + (date.getMonth() + 1)).slice(-2)}-${("0" + (date.getDay() + 1)).slice(-2)}`;
    let isInfraction=false;
    this.accelerometerService.getAcceleration((data: any) => {
      this.accelerationData = data.accelerationIncludingGravity;
      if ((this.accelerationData.x-0) >=3 || this.accelerationData.y<9 || this.accelerationData.z<4.5)  isInfraction = true;
      if (this.accelerationData.x < -1.5 || this.accelerationData.y< -1.5 || this.accelerationData.z<-1.5)  isInfraction = true;
      if (isInfraction){
        this.hackatonService.accelerometre(this.accelerationData).subscribe();
        isInfraction=false
      }
      this.hackatonService.getInfractionsDuMois().subscribe((response)=>{

      });
      this.distanceTrackerService.startTracking();
      this.distanceTrackerService.distanceSubject.subscribe(distance => {
        this.statistiquesConduite.kilometresParcourus = distance;
        console.log(distance);
        //this.hackatonService.sendDistanceParcourue(distance,todaysDate);
      },
        (error) => {
          console.error('Erreur lors de la requête :', error);
        });
    });
  }
}
