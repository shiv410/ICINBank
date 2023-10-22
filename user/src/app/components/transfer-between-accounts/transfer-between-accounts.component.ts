import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TransferService } from 'src/app/services/transfer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transfer-between-accounts',
  templateUrl: './transfer-between-accounts.component.html',
  styleUrls: ['./transfer-between-accounts.component.css']
})
export class TransferBetweenAccountsComponent {

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private transferService: TransferService
  ) { }


  transferForm: FormGroup;
  loading = false;
  submitted = false;

  ngOnInit() {

    var username: string = localStorage.getItem("username")!;
    var accNo = +localStorage.getItem("savingAccNo")!;
    console.log(accNo)
    console.log(username)
    this.transferForm = this.formBuilder.group({
      username: username,
      saccountNo: accNo,
      ifscNo: ['', [Validators.required, Validators.minLength(8)]],
      raccountNo: ['', [Validators.required]],
      amount: ['', [Validators.required]]
    });

  }
  get saccountno(): any {
    return localStorage.getItem('savingAccNo');
  }

  get fval() { return this.transferForm.controls; }

  transfer() {
    this.submitted = true;
    if (this.transferForm.invalid) {
      return;
    }
    this.loading = true;
    const result: any = Object.assign({}, this.transferForm.value);


    try {
      this.transferService.insertEntry(result.username, result.saccountNo, result.ifscNo, result.raccountNo, result.amount).subscribe(
        (data: any) => {
          this.loading = false;
          if (data.transferStatus == true) {
            Swal.fire({
              icon: 'success',
              title: 'Transaction successful',
              text: data.responseMessage
            })
          }
          else {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: data.responseMessage,
            })
          }
        }
      );
    } catch {
      this.loading = false;
    }
  }

}
