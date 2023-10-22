import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { Observable } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  return true;
};


export class AuthGuard {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authenticationService.isAuthenticated) {
      return true;
    }
    this.router.navigate(['']);
    return false;
  }

}
