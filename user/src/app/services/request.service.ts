import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chequebookresponse } from '../model/chequebookresponse';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private rootUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  insertRequest(accNo: number, pages: number = 20) {
    var body = {
      account: accNo,
      no_of_pages: pages,
    }
    console.log(body);
    return this.http.post<Chequebookresponse>(this.rootUrl + '/cheque/request', body);
  }

}
