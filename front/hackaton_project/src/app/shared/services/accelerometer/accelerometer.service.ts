import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccelerometerService {
  private accelerometer: any;

  constructor() {
    if ('Accelerometer' in window) {
      // @ts-ignore
      this.accelerometer =  new window.Accelerometer({ frequency: 1 });
    } else {
      console.error('Accelerometer is not supported.');
    }
  }

  getAcceleration(callback: (acceleration: { accelerationIncludingGravity: { x: any; y: any; z: any } }) => void) {
    if (this.accelerometer) {
      this.accelerometer.addEventListener('reading', () => {
        callback({
          accelerationIncludingGravity: {
            x: this.accelerometer.x,
            y: this.accelerometer.y,
            z: this.accelerometer.z
          }
        });
      });
      this.accelerometer.start();
    } else {
      console.error('Accelerometer is not supported.');
    }
  }
}
