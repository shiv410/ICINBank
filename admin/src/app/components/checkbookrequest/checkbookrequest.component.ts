import { Component } from '@angular/core';
import { Checkbookrequest } from 'src/app/model/checkbookrequest';
import { CheckbookService } from 'src/app/service/checkbook.service';

@Component({
  selector: 'app-checkbookrequest',
  templateUrl: './checkbookrequest.component.html',
  styleUrls: ['./checkbookrequest.component.css']
})
export class CheckbookrequestComponent {

  checkbookrequests: Checkbookrequest[];
  term: string;

  constructor(
    public checkbookService: CheckbookService
  ) { }

  ngOnInit() {
    this.checkbookService.getRequestsData().subscribe(res => {
      this.checkbookrequests = res
    });
  }

  getData() { }

  confirmRequest(account: any) {
    this.checkbookService.confirmCheckbookService(account).subscribe(res => this.ngOnInit());
  }

}
