import { Component, OnInit } from '@angular/core';
import { VoitureService } from '../_services/voiture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from './Client';
import {ClienttService} from '../_services/client.service';

@Component({templateUrl: './view.component.html'})
export class ClientViewComponent implements OnInit {

  id: number;
  product: Client;

  constructor(
    public productService: ClienttService,
    private route: ActivatedRoute,
    private router: Router
   ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.productService.find(this.id).subscribe((data: Client)=>{
      this.product = data;
    });
  }

}
