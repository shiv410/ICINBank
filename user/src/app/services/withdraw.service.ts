import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WithdrawService {

  private api = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  insertEntry(account: string, amount: number) {
    var body = {
      account: account,
      amount: amount,
    };
    console.log(body);
    return this.http.post(this.api + '/account/withdraw', body);
  }
}
