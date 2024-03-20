import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConfigService} from "../config.service";
import {Observable, tap} from "rxjs";
import {Accelerometre} from "../../model/accelerometre";

@Injectable({
  providedIn: 'root'
})
export class HackatonService {
  constructor(private http: HttpClient, private  config: ConfigService) { }

  accelerometre(accelerometre : Accelerometre): Observable<any> {
    const url = `${this.config.baseUrl}inscription`;
    return this.http.post(url, accelerometre, {observe: 'response'}).pipe(
      tap(response => {
        console.log(response)
      })
    );
  }
  getJoueurParNom() : Observable<any>{
    return this.http.get<any>(`${this.config.baseUrl}`,{observe: 'response'});
  }
}
