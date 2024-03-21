export class Accelerometre {
  x : number;
  y : number;
  z : number;
  date : string;

  constructor(x: number, y: number, z: number, date: string) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.date = date;
  }
}
