import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Userdisplay } from '../model/userdisplay';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8080"
  }
  public getUser(username: string): Observable<Userdisplay> {
    return this.http.get<Userdisplay>(this.url + "/home/" + username);
  }

}
