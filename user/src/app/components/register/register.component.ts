import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { RegisterService } from 'src/app/services/register.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private registerService: RegisterService
  ) { }


  identityType = [
    { name: "Aadhar Card", value: "aadhar" },
    { name: "PAN card", value: "pancard" },
    { name: "Passport", value: "passport" },
    { name: "Voter Id Card", value: "voter" }
  ]


  registerForm: FormGroup;
  loading = false;
  submitted = false;
  selectedOption: string;


  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      userName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      dob: ['', Validators.required],
      phone: ['', Validators.required],
      address: ['', Validators.required],
      identityType: ['', Validators.required],
      file: ['', Validators.required],
      identity: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  get fval() { return this.registerForm.controls; }


  onFormSubmit() {
    this.submitted = true;
    // return for here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    const result: User = Object.assign({}, this.registerForm.value);


    // Do useful stuff with the gathered data
    console.log(result.firstName);


    this.registerService.insertUser(result.firstName, result.lastName, result.userName, result.password, result.dob, result.phone, result.address, this.selectedOption, result.identity, result.email).subscribe(
      (data: any) => {
        this.loading = false;
        localStorage.clear();
        localStorage.setItem('user', JSON.stringify(data));
        if (data.registrationStatus == true) {
          Swal.fire(
            {
              icon: 'success',
              title: 'User registered succesfully!',
              text: "Please wait for an email for account activation!"
            }
          )
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: data.responseMessage,
          })
        }
        this.router.navigate(['/login']);
      }
    );
  }


  // filterSelected(selectedOption: any) {
  //   this.selectedOption = selectedOption;
  //   console.log('selected value= ' + selectedOption);
  // }
  // filterSelected(event: Event) {
  //   const target = event.target as HTMLSelectElement;
  //   const value = target.value;
  //   // Now use the value for your needs
  //   this.selectedOption = this.selectedOption;
  //   console.log('selected value= ' + this.selectedOption);
  // }

  filterSelected(event: Event) {
    const element = event.target as HTMLSelectElement;
    this.selectedOption = element.value;  // Because element.value returns a string, this is safe.
    console.log('selected value= ' + this.selectedOption);
  }


}
