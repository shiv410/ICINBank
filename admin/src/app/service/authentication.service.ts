import { Injectable } from '@angular/core';
import { Logindata } from '../model/logindata';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly adminUser = new Logindata('shivani', 'shiv');
  isAuthenticated = false;
  constructor(private router: Router) { }

  authenticate(login: Logindata): boolean {
    if (this.checkCredentials(login)) {
      this.isAuthenticated = true;
      this.router.navigate(['../useraccount']);
      return true;
    }
    alert("Incorrect Login or Password");
    this.router.navigate(['../']);
    this.isAuthenticated = false;
    return false;
  }

  checkCredentials(login: Logindata): boolean {
    return this.checkEmail(login.username) && this.checkPassword(login.password);
  }

  checkEmail(email: string): boolean {
    return email === this.adminUser.username;
  }

  checkPassword(password: string): boolean {
    return password === this.adminUser.password;
  }

  logout() {
    this.isAuthenticated = false;
    this.router.navigate(['']);
  }

}
