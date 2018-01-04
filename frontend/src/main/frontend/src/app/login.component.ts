import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms'; // <-- NgModel lives here

import { Component }   from '@angular/core';
import { Router }      from '@angular/router';
import { AuthService } from './auth.service';

import { User } from './_models/user';

@Component({
  templateUrl: './login.component.html'
  })
export class LoginComponent {
  message: string;
  user: User = {
    username: '',
    password: 'test',
    firstName: 'test',
    lastName: 'test'
  };

  constructor(public authService: AuthService, public router: Router) {
    this.setMessage();
  }

  setMessage() {
    //this.message = 'Вход ' + (this.authService.isLoggedIn ? 'выполнен' : 'требуется');
    this.message = 'Вход ' + (this.checkLocalStorage() ? 'выполнен' : 'требуется');
  }

  login() {
    if (this.user.username == '') {this.message = 'Введите имя пользователя.'; return;}
    this.message = 'Попытка входа ...';
    
    this.authService.login(this.user.username).subscribe(() => {
      this.setMessage();
      //if (this.authService.isLoggedIn) {
      if (localStorage.getItem('currentUser')) {  
        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/acc';

        // Redirect the user
        this.router.navigate([redirect]);
      }
    });
  }

  checkLocalStorage(): boolean{
  if (localStorage.getItem('currentUser')) { return true; }  
  else return false;
}

  logout() {
    this.authService.logout();
    this.setMessage();
  }
}