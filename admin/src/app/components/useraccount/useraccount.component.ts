import { Component } from '@angular/core';
import { DisableService } from 'src/app/service/disable.service';
import { EnableService } from 'src/app/service/enable.service';
import { AuthorizationService } from 'src/app/service/authorization.service';
import { Userdata } from 'src/app/model/userdata';
import { Authorizeuser } from 'src/app/model/authorizeuser';
import { FeaturesService } from 'src/app/service/features.service';


@Component({
  selector: 'app-useraccount',
  templateUrl: './useraccount.component.html',
  styleUrls: ['./useraccount.component.css']
})
export class UseraccountComponent {

  users: any[];
  selectedOption: string;
  selectedValue: number;

  roles = [
    { name: " ", value: 0 },
    { name: "Deposit Access", value: 1 },
    { name: "Deposit + Withdraw Access", value: 2 },
    { name: "Deposit + Withdraw + Transfer Access", value: 3 }
  ]

  constructor(
    private enable: EnableService,
    private disable: DisableService,
    private authservice: AuthorizationService,
    private featureService: FeaturesService
  ) { }

  ngOnInit(): void {
    this.authservice.getAllUsers().subscribe(res => {
      console.log(res)
      //console.log(res[0].featureStatus)
      res.forEach(element => {

        console.log(element.featureStatus)
        // console.log(this.roles[1].name)
        if (element.featureStatus == 1) {
          element.featureStatus = this.roles[1].name
        }
        if (element.featureStatus == 2) {
          element.featureStatus = this.roles[2].name
        }
        if (element.featureStatus == 3) {
          element.featureStatus = this.roles[3].name
        }
      });
      this.users = res

    });
  }

  getAllUsers() {
    this.authservice.getRequestData().subscribe(
      res => {
        console.log(res);
        this.users = JSON.parse(JSON.stringify(res));
      },
      error => console.log(error)
    )
  }


  enableLoginService(username: string) {
    console.log(username)
    this.enable.enableLoginService(username).subscribe(res => this.ngOnInit());
    //this.enableService.enableLoginService();
  }

  disableLoginService(username: string) {
    this.disable.disableLoginService(username).subscribe(res => this.ngOnInit());
    //this.disableService.disableLoginService();
  }

  setOption(username: string) {
    this.featureService.setFeatures(username, this.selectedValue).subscribe(res => this.ngOnInit());

    //this.featuresService.setFeatures();
  }

  // filterSelected(event: Event) {
  //   this.selectedValue = this.selectedValue
  //   console.log('selected value= ' + this.selectedValue);
  // }

  // filterSelected(event: Event) {
  //   const element = event.target as HTMLSelectElement;
  //   this.selectedOption = element.value;  // Because element.value returns a string, this is safe.
  //   console.log('selected value= ' + this.selectedOption);
  // }

  // filterSelected(event: any) {
  //   const value = event.target.value;
  //   if (value !== null && value !== undefined) {
  //     this.selectedOption = value;
  //     console.log('selected value=', this.selectedOption);
  //   }
  // }

  filterSelected(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const selectedValue = selectElement.value;
    console.log('Selected value:', selectedValue);
  }

}
