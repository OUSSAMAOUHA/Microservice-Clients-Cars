import { Component, OnInit } from '@angular/core';
import { VoitureService } from '../_services/voiture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Voiture } from './voiture';

@Component({templateUrl: './view.component.html'})
export class ProductViewComponent implements OnInit {

  id: number;
  product: Voiture;

  constructor(
    public productService: VoitureService,
    private route: ActivatedRoute,
    private router: Router
   ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.productService.find(this.id).subscribe((data: Voiture)=>{
      this.product = data;
    });
  }

}
