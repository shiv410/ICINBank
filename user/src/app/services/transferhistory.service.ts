import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transferhistory } from '../model/transferhistory';

@Injectable({
  providedIn: 'root'
})
export class TransferhistoryService {

  private url: String;

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8080"
  }
  public getTransferHistory(accNo: string): Observable<Transferhistory[]> {
    return this.http.get<Transferhistory[]>(this.url + "/account/getTransfers/" + accNo);
  }

}
