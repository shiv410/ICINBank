import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { UseraccountComponent } from './components/useraccount/useraccount.component';
import { authGuard } from './guard/auth.guard';
import { CheckbookrequestComponent } from './components/checkbookrequest/checkbookrequest.component';
import { AdminregisterComponent } from './components/adminregister/adminregister.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'useraccount', component: UseraccountComponent, canActivate: [authGuard] },
  { path: 'ckeckbook', component: CheckbookrequestComponent, canActivate: [authGuard] },
  { path: 'authorize', component: AdminregisterComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
