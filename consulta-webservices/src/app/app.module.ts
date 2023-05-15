import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { WebService1ViewComponent } from './web-service1-view/web-service1-view.component';
import { WebService2ViewComponent } from './web-service2-view/web-service2-view.component';

@NgModule({
  declarations: [
    AppComponent,
    WebService1ViewComponent,
    WebService2ViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
