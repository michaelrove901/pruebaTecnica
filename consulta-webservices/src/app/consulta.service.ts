import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private apiUrl1 = 'http://localhost:8080/api/transacciones';
  private apiUrl2 = 'http://localhost:8080/api/tarjetas'; 

  constructor(private http: HttpClient) { }

  consultarWebService1(): Observable<any> {
    return this.http.get<any>(this.apiUrl1);
  }

  consultarWebService2(): Observable<any> {
    return this.http.get<any>(this.apiUrl2);
  }
}