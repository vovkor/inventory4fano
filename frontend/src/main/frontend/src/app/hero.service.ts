import { Injectable }    from '@angular/core';
import { Headers, Http, Response  } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


import { Hero } from './hero';
import { HEROES } from './mock-heroes';


//Angular may need to inject other dependencies into this service.
@Injectable()
export class HeroService{
//A Promise is ... well it's a promise to call us back later when the results are ready. We ask an asynchronous service to do some work and give it a callback function. It does that work (somewhere) and eventually it calls our function with the results of the work or an error  
    //getHeroes(): Promise<Hero[]> {
      //return Promise.resolve(HEROES);
   // } 
private headers = new Headers({'Content-Type': 'application/json'});
// сделал файл proxy.conf.json   но не заработало. Надо потом посмотреть. Пока использую полный url
// private heroesUrl = 'api/rest/student';  // URL to web api
//private heroesUrl = 'http://localhost:8080/rest/student';  // URL to web api

private heroesUrl = 'http://91.151.189.38:8080/greeting/';

  constructor(private http: Http) { }
/*
  getHeroes__Promise_version(): Promise<Hero[]> {
    return this.http.get(this.heroesUrl)
               .toPromise()
               .then(this.extractData)//.then(response => response.json().data as Hero[])
               .catch(this.handleError);
  }
  private handleError_Promise_version(error: any): Promise<any> {
    console.error('Произошла ошибка!!!', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }  
getHero_Promise_version(id: number): Promise<Hero> {
  return this.getHeroes()
             .then(heroes => heroes.find(hero => hero.id === id));
}  
getHero_Promise_version(id: number): Promise<Hero> {
  return this.getHeroes()
             .then(heroes => heroes.find(hero => hero.id === id));
}           
*/

  getHeroes(): Observable<Hero[]> {
    return this.http.get(this.heroesUrl)
                .map(this.extractData)
                      //.get(heroesUrl, { search: params })
    //).subscribe((data) => console.log(data))
                .catch(this.handleError);
  }
     
 private extractData(res: Response) {
  let body = res.json();
 return body;//.data|| { }; //Промучился долго, не работал с моим json пока не закомментировал!!!
}


private handleError (error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
     errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }


getHero(id: number): Observable<Hero> {
  const url = `${this.heroesUrl}/${id}`;
   return this.http.get(url)
                .map(this.extractData)
                .catch(this.handleError);
}    
}