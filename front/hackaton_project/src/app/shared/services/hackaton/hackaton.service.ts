import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {Accelerometre} from "../../model/accelerometre";

@Injectable({
  providedIn: 'root'
})
export class HackatonService {

  constructor(private http: HttpClient) { }

  accelerometre(accelerometre : Accelerometre): Observable<any> {
    const url = 'http://localhost:8081/accelerometre';
    return this.http.post(url, accelerometre, {observe: 'response', responseType: 'text' });
  }
  sendDistanceParcourue(distance : number, date : string): Observable<any> {
    const url = 'http://localhost:8081/distanceParcourue';
    return this.http.post(url, {distance, date}, {observe: 'response', responseType: 'text' });
  }
  getInfractionsDuMois() : Observable<any>{
    return this.http.get<any>('http://localhost:8081/informationsScore',{observe: 'response'});
  }
}
