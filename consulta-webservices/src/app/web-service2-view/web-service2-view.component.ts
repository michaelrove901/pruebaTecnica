import { Component, OnInit } from '@angular/core';
import { ConsultaService } from '../consulta.service';
@Component({
  selector: 'app-web-service2-view',
  template: `
  <div class="container">
    
    <h1>Vista de Tarjetas</h1>

    <div *ngIf="datos">
      <table class="table table-bordered table-striped">
        <thead class="thead-dark">
          <tr>
            <th># Tarjeta</th>
            <th>Titular</th>
            <th>Cédula</th>
            <th>Tipo</th>
            <th>Teléfono</th>
            <th>Número de Validación</th>
            <th>Estado</th>
            <th>Enmascarado PAN</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let dato of datos">
            <td>{{ dato.id }}</td>
            <td>{{ dato.titular }}</td>
            <td>{{ dato.cedula }}</td>
            <td>{{ dato.tipo }}</td>
            <td>{{ dato.telefono }}</td>
            <td>{{ dato.numeroValidacion }}</td>
            <td>{{ dato.status }}</td>
            <td>{{ dato.enmascaradoPAN }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    </div>
  `,
})
export class WebService2ViewComponent implements OnInit {
  datos: any;

  constructor(private consultaService: ConsultaService) {}

  ngOnInit() {
    this.consultaService.consultarWebService2().subscribe(
      (response) => {
        this.datos = response;
      },
      (error) => {
        console.log(error);
      }
    );
    }
  }
