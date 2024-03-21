import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccelerometerService {
  private accelerometer: any;

  constructor() {
    if ('Accelerometer' in window) {
      // @ts-ignore
      this.accelerometer =  new window.Accelerometer({ frequency: 60 });
    } else {
      console.error('Accelerometer is not supported.');
    }
  }

  getAcceleration(callback: (acceleration: { accelerationIncludingGravity: { x: any; y: any; z: any; date:any }; }) => void) {
    const date = new Date();
    if (this.accelerometer) {
      this.accelerometer.addEventListener('reading', () => {
        callback({
          accelerationIncludingGravity: {
            x: this.accelerometer.x,
            y: this.accelerometer.y,
            z: this.accelerometer.z,
            date : `${date.getFullYear()}-${("0" + (date.getMonth() + 1)).slice(-2)}-${("0" + (date.getDay() + 1)).slice(-2)}`
          },
        });
      });
      this.accelerometer.start();
    } else {
      console.error('Accelerometer is not supported.');
    }


  }
}
