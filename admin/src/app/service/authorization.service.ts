import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Authorizeuser } from '../model/authorizeuser';
import { Observable } from 'rxjs';
import { Userdata } from '../model/userdata';


@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private rootUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<any[]>(this.rootUrl + '/all');
  }

  getRequestData() {
    return this.http.get<any[]>(this.rootUrl + '/unauthorized/all');
  }

  authorizeAccount(username: Authorizeuser) {
    return this.http.get(this.rootUrl + username + '/authorize');
  }

  rejectRequest(username: Authorizeuser) {
    return this.http.get(this.rootUrl + username + '/authorize/cancel');
  }

}
