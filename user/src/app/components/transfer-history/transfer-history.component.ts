import { Component } from '@angular/core';
import { Transferhistory } from 'src/app/model/transferhistory';
import { TransferhistoryService } from 'src/app/services/transferhistory.service';

@Component({
  selector: 'app-transfer-history',
  templateUrl: './transfer-history.component.html',
  styleUrls: ['./transfer-history.component.css']
})
export class TransferHistoryComponent {

  private accNo: any = +localStorage.getItem("savingAccNo")!;
  public transferList: Array<Transferhistory>;

  constructor(private transferService: TransferhistoryService) { }

  ngOnInit(): void {
    this.transferService.getTransferHistory(this.accNo).subscribe(res => {
      this.transferList = res;
    });
  }

}
