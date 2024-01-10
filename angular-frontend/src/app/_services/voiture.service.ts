import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {  Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AppConstants } from '../common/app.constants';
import { Voiture } from '../voitures/voiture';

@Injectable({providedIn: 'root'})
export class VoitureService {

	constructor(private httpClient: HttpClient) {
	}

	getAll(request): Observable<any> {
		const params = request;
		return this.httpClient.get('http://localhost:8082/api/' + 'voitures', {params});
	}
  getAllVoiture(): Observable<any> {
    return this.httpClient.get('http://localhost:8082/api/voitures/temporarily-received');
  }
	create(product:any): Observable<any> {
		return this.httpClient.post('http://localhost:8082/api/' + 'voitures', JSON.stringify(product), AppConstants.httpOptions)
	}

	find(id:number): Observable<any> {
		return this.httpClient.get<Voiture>('http://localhost:8082/api/' + 'voitures/' + id)
	}

	update(id:number, product: Voiture): Observable<any> {
		return this.httpClient.put('http://localhost:8082/api/' + 'voitures/' + id, JSON.stringify(product), AppConstants.httpOptions)
	}

	delete(id:number){
		return this.httpClient.delete('http://localhost:8082/api/' + 'voitures/' + id)
	}
}
