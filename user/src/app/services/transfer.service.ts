import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private rootUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  insertEntry(username: string, saccount: string, ifscNo: string, raccount: string, amount: number) {
    var body = {
      username: username,
      saccount: saccount,
      ifsc: ifscNo,
      raccount: raccount,
      amount: amount
    }
    console.log(body);
    return this.http.post(this.rootUrl + '/account/transfer', body);
  }

}
