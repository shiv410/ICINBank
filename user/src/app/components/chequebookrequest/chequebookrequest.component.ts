import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { RequestService } from 'src/app/services/request.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-chequebookrequest',
  templateUrl: './chequebookrequest.component.html',
  styleUrls: ['./chequebookrequest.component.css']
})
export class ChequebookrequestComponent {

  requestingAccNo: number = +localStorage.getItem("savingAccNo")!;

  selectedValue: number;

  loading: boolean = false;

  pages = [
    { name: "20", value: 20 },
    { name: "50", value: 50 },
    { name: "75", value: 75 }
  ]

  filterSelected(value: any) {
    this.selectedValue = value;
  }

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private requestService: RequestService,

  ) { }

  ngOnInit(): void { }


  setOption() {
    this.loading = true;
    if (this.selectedValue == null) {
      this.selectedValue = 20;
    }
    try {
      this.requestService.insertRequest(this.requestingAccNo, +this.selectedValue).subscribe((res: any) => {
        console.log(res);
        this.loading = false;
        if (res.status == true) {
          Swal.fire(
            {
              icon: 'success',
              title: 'Chequebook request placed!',
              text: res.responseMessage
            }
          )
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: res.responseMessage,
          })
        }
      });
    }
    catch {
      this.loading = false;
    }
  }

}
