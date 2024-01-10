import { Component, OnInit } from '@angular/core';
import { VoitureService } from '../_services/voiture.service';
import { Voiture } from './voiture';
import { PageEvent } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialog } from './../dialog/confirmation-dialog.component';

@Component({templateUrl: './list.component.html'})
export class ProductListComponent implements OnInit {

	products: Voiture[] = [];
	totalElements: number = 0;
  voitures: Voiture[] = [];

	constructor(public productService: VoitureService, private dialog: MatDialog) {
	}

	ngOnInit(): void {
		this.getProducts({ page: "0", size: "5" });
    this.getVoituresByClientId();
	}

	private getProducts(request) {
		this.productService.getAll(request)
		.subscribe(data => {
			this.products = data['content'];
			this.totalElements = data['totalElements'];
      console.log(this.products)
		}
		, error => {
			console.log(error.error.message);
		}
		);
	}
  private getVoituresByClientId() {
    this.productService.getAllVoiture()
      .subscribe(data => {
          this.voitures = data;
          console.log(this.voitures)
        }
        , error => {
          console.log(error.error.message);
        }
      );
  }

	nextPage(event: PageEvent) {
		const request = {};
		request['page'] = event.pageIndex.toString();
		request['size'] = event.pageSize.toString();
		this.getProducts(request);
	}

	deleteProduct(id:number){
		this.productService.delete(id)
		.subscribe(data => {
			this.products = this.products.filter(item => item.id !== id);
			console.log('Client deleted successfully!');
		}
		, error => {
			console.log(error.error.message);
		}
		);
	}

	openDialog(id:number) {
		const dialogRef = this.dialog.open(ConfirmationDialog,{
		data:{
			message: 'Do you want to delete the product and the associated licenses?'
		}
		});

		dialogRef.afterClosed().subscribe((confirmed: boolean) => {
			if (confirmed) {
				this.deleteProduct(id);
			}
		});
	}
}
