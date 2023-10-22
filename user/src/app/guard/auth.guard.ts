import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';

export const authGuard: CanActivateFn = (_route, _state) => {
  return true;
};

export class AuthGuard {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }


  canActivate()
  // _next: ActivatedRouteSnapshot,
  // _state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  {
    if (this.authService.isAuthenticated) {
      return true;
    }
    this.router.navigate(['']);
    return false;
  }

}
