import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrl: './connexion.component.scss'
})
export class ConnexionComponent {
  connexionForm: FormGroup;

  constructor(private formBuilder: FormBuilder,private router: Router) {}

  ngOnInit(): void {
    this.connexionForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  seConnecter() {
    if (this.connexionForm.valid) {
        this.router.navigate(['/resultat']);
      }
    }

}
