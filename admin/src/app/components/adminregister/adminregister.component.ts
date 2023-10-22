import { Component } from '@angular/core';
import { Authorizeuser } from 'src/app/model/authorizeuser';
import { AuthorizationService } from 'src/app/service/authorization.service';

@Component({
  selector: 'app-adminregister',
  templateUrl: './adminregister.component.html',
  styleUrls: ['./adminregister.component.css']
})
export class AdminregisterComponent {

  authorizeusers: Authorizeuser[];

  constructor(
    public authorizeService: AuthorizationService
  ) { }

  ngOnInit() {
    this.authorizeService.getRequestData().subscribe(res => {
      console.log(res);
      this.authorizeusers = res
    });
  }

  authorizeAccount(username: any) {
    this.authorizeService.authorizeAccount(username).subscribe(res => this.ngOnInit());
  }

  rejectRequest(username: any) {
    this.authorizeService.rejectRequest(username).subscribe(res => this.ngOnInit());
  }

}
