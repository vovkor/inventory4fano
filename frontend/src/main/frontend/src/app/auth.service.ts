//Although it doesn't actually log in, it has what you need for this discussion. 
//It has an isLoggedIn flag to tell you whether the user is authenticated. 
//Its login method simulates an API call to an external service by returning 
//an Observable that resolves successfully after a short pause. 
//The redirectUrl property will store the attempted URL so you can navigate to it after authenticating.

import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams, RequestOptions  } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'; // map надо импортировать отдельно? хотя это метод класса Observable. Кстати, работает если закоммент


@Injectable()
export class AuthService {
  isLoggedIn: boolean = false;
  public accUrl =   'http://91.151.189.38:8080/acc';
  public token: string;
  // store the URL so we can redirect after logging in
  redirectUrl: string;


  constructor(private http: Http) {
      // set token if saved in local storage
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      //this.token = currentUser && currentUser.token;
  }

  login(username: string): Observable<boolean> {
    //return Observable.of(true).delay(1000).do(val => this.isLoggedIn = true);
    //return Observable.of(true).delay(1000).do(val => {localStorage.setItem('currentUser','serhl'); this.isLoggedIn = true} );
    //Методы объекта Http после выполнения запроса возвращают объект Observable
    const url = `${this.accUrl}/auth/${username}`;
    return this.http.get(url)
                    .map((resp:Response)=>{
                     let token = resp.json();
                     
console.log(' login(),  token = '+token+'; '+username);                      
                      if (token){
                        localStorage.setItem('currentUser', username);
                        return true;
                      }
                      else { return false;}
                    }); 
    
  }

  logout(): void {
    //this.isLoggedIn = false;
    localStorage.removeItem('currentUser');

  }
}
