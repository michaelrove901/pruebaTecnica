import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WebService1ViewComponent } from './web-service1-view/web-service1-view.component';
import { WebService2ViewComponent } from './web-service2-view/web-service2-view.component';

const routes: Routes = [
  { path: 'webservice1', component: WebService1ViewComponent },
  { path: 'webservice2', component: WebService2ViewComponent },
  { path: '', redirectTo: '/webservice1', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }