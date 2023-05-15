import { Component, OnInit } from '@angular/core';
import { ConsultaService } from '../consulta.service';

@Component({
  selector: 'app-web-service1-view',
  template: `<div class="container">
    <h1>Vista de Transacciones</h1>
    <div *ngIf="datos">
      <table class="table table-bordered table-striped">
        <thead class="thead-dark">
          <tr>
            <th># Transaccion</th>
            <th>Estado</th>
            <th>Fecha Creación</th>
            <th># Tarjeta</th>
            <th>Precio Compra</th>
            <th>Dirección</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let dato of datos">
            <td>{{ dato.id }}</td>
            <td>{{ dato.estado }}</td>
            <td>{{ dato.fechaCreacion }}</td>
            <td>{{ dato.tarjeta }}</td>
            <td>{{ dato.totalCompra }}</td>
            <td>{{ dato.direccion }}</td>
          </tr>
        </tbody>
      </table>
    </div> </div>
  `,
})
export class WebService1ViewComponent implements OnInit {
  datos: any;

  constructor(private consultaService: ConsultaService) {}

  ngOnInit() {
    this.consultaService.consultarWebService1().subscribe(
      (response) => {
        this.datos = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}