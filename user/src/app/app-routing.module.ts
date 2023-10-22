import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TransferBetweenAccountsComponent } from './components/transfer-between-accounts/transfer-between-accounts.component';
import { AuthGuard, authGuard } from './guard/auth.guard';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';
import { ChequebookrequestComponent } from './components/chequebookrequest/chequebookrequest.component';
import { TransferHistoryComponent } from './components/transfer-history/transfer-history.component';
import { EditprofileComponent } from './components/editprofile/editprofile.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'transfer', component: TransferBetweenAccountsComponent, canActivate: [authGuard] },
  { path: 'transactionHistory', component: TransactionHistoryComponent, canActivate: [authGuard] },
  { path: 'chequebookRequest', component: ChequebookrequestComponent, canActivate: [authGuard] },
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'transferHistory', component: TransferHistoryComponent, canActivate: [authGuard] },
  { path: 'editProfile', component: EditprofileComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
