import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GpsService {
  startLat: number;
  startLon: number;
  totalDistance: number = 0;
  distanceSubject: Subject<number> = new Subject<number>();

  constructor() { }

  deg2rad(deg: number): number {
    return deg * (Math.PI / 180);
  }

  calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
    const R = 6371; // Rayon de la Terre en kilomètres
    const dLat = this.deg2rad(lat2 - lat1);
    const dLon = this.deg2rad(lon2 - lon1);
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) *
      Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // Distance en kilomètres
    return distance;
  }

  startTracking(): void {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.startLat = position.coords.latitude;
        this.startLon = position.coords.longitude;
        const options = {
          enableHighAccuracy: false,
          timeout: 5000,
          maximumAge: 0
        };

        navigator.geolocation.watchPosition(
          (position) => {
            const currentLat = position.coords.latitude;
            const currentLon = position.coords.longitude;
            const distance = this.calculateDistance(this.startLat, this.startLon, currentLat, currentLon);
            this.totalDistance += distance;
            this.distanceSubject.next(this.totalDistance);
            this.startLat = currentLat;
            this.startLon = currentLon;
          },
          (error) => {
            console.error('Erreur de géolocalisation :', error);
          },
          options
        );
      });
    } else {
      console.error('La géolocalisation n\'est pas prise en charge par ce navigateur.');
    }
  }
}
