import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Client} from '../clients/Client';
import {ClienttService} from '../_services/client.service';
import {VoitureService} from '../_services/voiture.service';
import {Voiture} from '../voitures/voiture';

@Component({
  selector: 'app-board-moderator',
  templateUrl: './board-moderator.component.html',
  styleUrls: ['./board-moderator.component.css']
})
export class BoardModeratorComponent implements OnInit {

  content: string;
  form: FormGroup;
  client: Client;
  voitures: Voiture[] = [];

  constructor(private userService: UserService, private formBuilder: FormBuilder, public productService: ClienttService,public voitureService: VoitureService) {
    this.form = this.formBuilder.group({
      searchClientId: ['', Validators.required], // Ajout du champ de recherche
    });
  }


  // tslint:disable-next-line:typedef
  searchClient() {
    const clientId = this.form.get('searchClientId').value;
    this.productService.getVoitureByClient(clientId).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.error(error);
      });
    this.voitureService.getAllVoiture().subscribe(
      data1 => {
        this.voitures = data1;
        console.log('dattaaaa' + this.voitures)
      }
      , error => {
        console.log(error.error.message);
      }
    );
   }

  ngOnInit(): void {
    this.userService.getModeratorBoard().subscribe(
      data => {
        this.content = data;

      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
