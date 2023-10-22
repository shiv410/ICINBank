import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeaturesService {

  id: number

  readonly rootUrl = 'http://localhost:8080/user/';

  constructor(private http: HttpClient) { }

  // setFeatures(username: any, value: any) {
  //   this.id = value
  //   console.log(this.id)
  //   return this.http.get(this.rootUrl + username + '/features/' + value);
  // }


  setFeatures(username: string, value: number | string): Observable<any> {
    // Ensure that parameters are defined
    if (!username || value === undefined || value === null) {
      console.error('Invalid parameters!', { username, value });
      return throwError('Invalid parameters!');
    }
    const url = `${this.rootUrl}${username}/features/${value}`;
    return this.http.get(url);
  }


}
