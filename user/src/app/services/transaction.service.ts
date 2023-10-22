import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../model/transaction';
import { Savingaccount } from '../model/savingaccount';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private url: String;

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8080"
  }

  public getTransactions(accNo: any): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.url + "/account/getHistory/" + accNo);
  }

  public getSavingAccount(username: string): Observable<Savingaccount> {
    return this.http.get<Savingaccount>(this.url + "/account/getsaving/" + username);
  }

}
