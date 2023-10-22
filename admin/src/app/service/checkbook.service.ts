import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Checkbookrequest } from '../model/checkbookrequest';

@Injectable({
  providedIn: 'root'
})
export class CheckbookService {

  readonly rootUrl = 'http://localhost:8080/user/';

  readonly dataUrl = 'http://localhost:8080/chequebook/request/all';

  private data: any = []

  constructor(private http: HttpClient) { }

  confirmCheckbookService(account: number) {
    return this.http.get(this.rootUrl + account + '/chequebook/request/confirm');
  }

  getRequestsData(): Observable<Checkbookrequest[]> {
    return this.http.get<Checkbookrequest[]>(this.dataUrl);
  }

}
