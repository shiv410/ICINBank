import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Logindata } from 'src/app/model/logindata';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private authenticationService: AuthenticationService
  ) { }

  submitted = false;

  ngOnInit(): void {
  }

  onSubmit(loginForm: NgForm) {
    this.submitted = true;
    if (loginForm.valid) {
      const loginDataInstance = new Logindata(loginForm.value.inputUserName, loginForm.value.password);
      this.authenticationService.authenticate(loginDataInstance);
    }
  }

}
