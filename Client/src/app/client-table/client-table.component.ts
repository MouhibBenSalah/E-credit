import { Component } from '@angular/core';

@Component({
  selector: 'app-client-table',
  templateUrl: './client-table.component.html',
  styleUrls: ['./client-table.component.css']
})
export class ClientTableComponent {
  authors = [
    {
      name: 'John Michael',
      email: 'john@creative-tim.com',
      age:'27',
      sexe:'Homme',
      salaire:'2000 DT',
      status: 'RISQUE',
      employed: '23/04/18',
      image: '../../assets/images/fcbk.png'
    },
    {
      name: 'Alexa Liras',
      email: 'alexa@creative-tim.com',
      age:'27',
      sexe:'Homme',
      salaire:'2000 DT',
      status: 'NORISK',
      employed: '11/01/19',
      image: '../../assets/images/fcbk.png'
    },
    {
      name: 'Laurent Perrier',
      email: 'laurent@creative-tim.com',
      age:'27',
      sexe:'Homme',
      salaire:'2000 DT',
      status: 'RISQUE',
      employed: '19/09/17',
     image: '../../assets/images/fcbk.png'
    },
    {
      name: 'Michael Levi',
      email: 'michael@creative-tim.com',
      age:'27',
      sexe:'Femme',
      salaire:'2000 DT',
      status: 'RISQUE',
      employed: '24/12/08',
      image: '../../assets/images/fcbk.png'
    },
    {
      name: 'Richard Gran',
      email: 'richard@creative-tim.com',
      age:'27',
      salaire:'2000 DT',
      status: 'RISQUE',
      sexe:'Femme',
      employed: '04/10/21',
      image: '../../assets/images/fcbk.png'
    },
    {
      name: 'Miriam Eric',
      email: 'miriam@creative-tim.com',
      age:'27',
      salaire:'2000 DT',
      status: 'RISQUE',
      sexe:'Femme',
      employed: '14/09/20',
      image: '../../assets/images/fcbk.png'
    }
  ];

}
