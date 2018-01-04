import { Injectable }       from '@angular/core';
import {
  CanActivate, Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
}                           from '@angular/router';
import { AuthService }      from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let url: string = state.url;

    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    // http://jasonwatmore.com/post/2016/08/16/angular-2-jwt-authentication-example-tutorial
   console.log(' зашли в checkLogin');
   //if (this.authService.isLoggedIn) { return true; } версия из доки
    if (localStorage.getItem('currentUser')) {
            // logged in so return true
             console.log(' return true;');
            return true;
        }

    

    // Store the attempted URL for redirecting
    this.authService.redirectUrl = url;

    // Navigate to the login page with extras
    this.router.navigate(['/login']);
    return false;
  }
}