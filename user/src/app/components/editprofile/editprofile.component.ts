import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Userupdate } from 'src/app/model/userupdate';
import { UpdateService } from 'src/app/services/update.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent {

  username: string = localStorage.getItem("username")!;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private updateService: UpdateService
  ) { }

  updateForm: FormGroup;
  loading = false;
  submitted = false;


  ngOnInit() {
    this.updateForm = this.formBuilder.group({
      phone: [''],
      address: [''],
      email: ['', [Validators.email]],
      prevpassword: ['', [Validators.minLength(6)]],
      password: ['', [Validators.minLength(6)]]

    });
  }
  get fval() { return this.updateForm.controls; }

  update() {
    this.submitted = true;
    // return for here if form is invalid
    if (this.updateForm.invalid) {
      return;
    }
    this.loading = true;

    const result: Userupdate = Object.assign({}, this.updateForm.value);

    try {
      this.updateService.update(this.username, result.phone, result.email, result.address, result.prevpassword, result.password).subscribe(
        (data: any) => {
          // localStorage.clear();
          // localStorage.setItem('user',JSON.stringify(data));
          console.log(data);
          this.loading = false;
          if (data.flag == true) {
            Swal.fire(
              {
                icon: 'success',
                title: 'Profile updated successfully!',
                text: data.message
              }
            )
          } else {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: data.message,
            })
          }
          this.router.navigate(['/editProfile']);
        }
      );
    } catch {
      this.loading = false;
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: "Something went wrong!"
      })
    }

  }

}
