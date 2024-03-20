import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  baseUrl = 'http://localhost:8082/api/utilisateur/v1/';
}
