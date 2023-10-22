import { Component } from '@angular/core';
import { Transaction } from 'src/app/model/transaction';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent {

  username: string = localStorage.getItem("username")!;
  accNo: number = +localStorage.getItem("savingAccNo")!;
  public transactionList: Array<Transaction>;
  public savingBalance: number;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.transactionService.getTransactions(this.accNo).subscribe(res => {
      this.transactionList = res;
      console.log(this.transactionList);
    });
    this.transactionService.getSavingAccount!(this.username).subscribe(res => {
      this.savingBalance = res.balance;
    });
  }

}
