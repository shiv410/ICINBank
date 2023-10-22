import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DisableService {

  readonly rootUrl = 'http://localhost:8080/user/';

  constructor(private http: HttpClient) { }

  disableLoginService(username: any) {
    return this.http.get(this.rootUrl + username + '/disable');
  }

}
