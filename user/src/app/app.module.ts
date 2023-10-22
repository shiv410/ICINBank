import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ChequebookrequestComponent } from './components/chequebookrequest/chequebookrequest.component';
import { EditprofileComponent } from './components/editprofile/editprofile.component';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';
import { TransferHistoryComponent } from './components/transfer-history/transfer-history.component';
import { TransferBetweenAccountsComponent } from './components/transfer-between-accounts/transfer-between-accounts.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ChequebookrequestComponent,
    EditprofileComponent,
    TransactionHistoryComponent,
    TransferHistoryComponent,
    TransferBetweenAccountsComponent,
    DepositComponent,
    WithdrawComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
