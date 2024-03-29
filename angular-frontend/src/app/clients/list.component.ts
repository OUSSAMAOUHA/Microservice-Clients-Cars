import { Component, OnInit } from '@angular/core';
import { VoitureService } from '../_services/voiture.service';
import { Client } from './Client';
import { PageEvent } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialog } from './../dialog/confirmation-dialog.component';
import {ClienttService} from '../_services/client.service';

@Component({templateUrl: './list.component.html'})
export class ClientListComponent implements OnInit {

	products: Client[] = [];
	totalElements: number = 0;

	constructor(public productService: ClienttService, private dialog: MatDialog) {
	}

	ngOnInit(): void {
		this.getProducts({ page: "0", size: "5" });
	}

	private getProducts(request) {
		this.productService.getAll(request)
		.subscribe(data => {
			this.products = data['content'];
			this.totalElements = data['totalElements'];
      console.log(data);
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
