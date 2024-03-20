import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  public mail ="hackaton@gmail.com";
  isUserDropdownVisible = false;

  constructor(private router: Router) {}

  toggleUserDropdown() {
    this.isUserDropdownVisible = !this.isUserDropdownVisible;
  }

  isConnexionRoute(): boolean {
    return this.router.url === '/connexion';
  }
}
