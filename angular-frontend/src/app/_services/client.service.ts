import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {  Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AppConstants } from '../common/app.constants';
import {Client} from '../clients/Client';

@Injectable({providedIn: 'root'})
export class ClienttService {

	constructor(private httpClient: HttpClient) {
	}

	getAll(request): Observable<any> {
		const params = request;
		return this.httpClient.get('http://localhost:8083/api/' + 'clients', {params});
	}
  getVoitureByClient(id: string): Observable<any> {
    const params = { id: id };
    return this.httpClient.post('http://localhost:8083/api/clients/send-id', null, { params: params });
  } 
	create(product:any): Observable<any> {
		return this.httpClient.post('http://localhost:8083/api/' + 'clients', JSON.stringify(product), AppConstants.httpOptions)
	}

	find(id:number): Observable<any> {
		return this.httpClient.get<Client>('http://localhost:8083/api/' + 'clients/' + id)
	}

	update(id:number, product:Client): Observable<any> {
		return this.httpClient.put('http://localhost:8083/api/' + 'clients/' + id, JSON.stringify(product), AppConstants.httpOptions)
	}

	delete(id:number){
		return this.httpClient.delete('http://localhost:8083/api/' + 'clients/' + id)
	}
}
