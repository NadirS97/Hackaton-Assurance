import {RouterModule, Routes} from '@angular/router';
import {ConnexionComponent} from "./connexion/connexion.component";
import {NgModule} from "@angular/core";
import {ResultatComponent} from "./resultat/resultat.component";

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'connexion'},
  {
    path: 'connexion',
    component : ConnexionComponent
  },
  {
    path: 'resultat',
    component : ResultatComponent
  }
];

@NgModule({
  declarations: [
  ],
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
