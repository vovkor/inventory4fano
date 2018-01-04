import { Injectable }    from '@angular/core';
import { Headers, Http, Response, URLSearchParams, RequestOptions  } from '@angular/http';
//import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'; // map надо импортировать отдельно? хотя это метод класса Observable. Кстати, работает если закоммент

import { Acc }      from './acc';

// нужен для создания потока. Из потока AccsComponent будет забирать полное количество образцов totalItems
import { Subject }  from 'rxjs/Subject';

//Angular may need to inject other dependencies into this service.
@Injectable()
export class AccService{
//A Promise is ... well it's a promise to call us back later when the results are ready. We ask an asynchronous service to do some work and give it a callback function. It does that work (somewhere) and eventually it calls our function with the results of the work or an error  
    //getHeroes(): Promise<Hero[]> {
      //return Promise.resolve(HEROES);
   // } 
  public totalItems: number = 0; 
  private headers = new Headers({'Content-Type': 'application/json'});
  // сделал файл proxy.conf.json   но не заработало. Надо потом посмотреть. Пока использую полный url
  // private heroesUrl = 'api/rest/student';  // URL to web api
  //private heroesUrl = 'http://localhost:8080/rest/student';  // URL to web api
  private accUrl =   'http://91.151.189.38:8080/acc';

  // Поток
  private totalCount = new Subject<number>();
  // Observable number stream
  totalCount$ = this.totalCount.asObservable(); 

  // --- Поток --- //
  // Observable number stream
  // Как понять, что парсинг закончился? Джобом периодически посылать запросы к backend?
  // наблюдаемый и подписка https://angular.io/docs/ts/latest/cookbook/component-communication.html#!#bidirectional-service
  // о генерации события смотри EventEmitter 
  
  constructor(private http: Http) { }
  // ---------------------------------- getAccs --------------------------------------------
  //Методы объекта Http после выполнения запроса возвращают объект Observable, который определен 
  //в библиотеке RxJS ("Reactive Extensions"). Она не является непосредственно частью Angular, 
  //однако широко используется особенно при взаимодействии с сервером по http. 
  //Эта библиотека реализует паттерн "асинхронный наблюдатель" (asynchronous observable). 
  //Так, выполнение запроса к серверу с помощью класса Http выполняются в асинхронном режиме.
  getAccs(page?: number, itemsPerPage?: number, countRec?: string): Observable<Acc[]> { // необязательные параметры через знак ?
    let tmpAccs: Observable<Acc[]>;

    let myParams: URLSearchParams = new URLSearchParams();
    myParams.append('userId', localStorage.getItem('currentUser'));
    let options = new RequestOptions({ headers: this.headers, params: myParams });
    let url = `${this.accUrl}/limit/${countRec}`;

//    return
    tmpAccs = this.http.get(url, options)
                //.map(this.extractData)
                  .map((resp:Response)=>{
                      let accList = resp.json();//.data;
                      let newAccs :Acc[] = [];
                      // передать через public переменную this.totalItems не получилось она передается в классе AccsComponent слишком поздно
                      this.totalItems = accList.length; 
                      console.log('totalItems='+this.totalItems); 
                      // поэтому делаю через поток (Observable stream)
                      //  this.totalCount отдает значение в totalItems нужен для Pagination в классе AccsComponent
                      this.totalCount.next(accList.length);
                        
                      for(let index in accList){ // можно пробежаться по массиву
                          //console.log(accList[index]);
                          let user = accList[index];
                          //newAccs.push({name: user.userName, age: user.userAge});
                      }
                      let start = (page - 1) * itemsPerPage;
                      let end = itemsPerPage > -1 ? (start + itemsPerPage) : accList.length;
                      //console.log(page + " start=" + start + " end=" + end);
                      newAccs = accList.slice(start,end);
                      //resp.headers. ("Access-Control-Allow-Origin", "*");
                      return newAccs;
                  })                
                //.get(accUrl, { search: params })
                //).subscribe((data) => console.log(data))
                .catch(this.handleError);

    return tmpAccs;
  }
  // ---------------------------------- parseFile --------------------------------------------
  getParce(ownerName: string): Observable<string> { 
    let tmpParce: Observable<string>;
    const url = `${this.accUrl}/parse/${ownerName}`;
    tmpParce = this.http.get(url)
                  .map((resp:Response)=>{
                      let res = resp.status;//.json();
                      console.log('res = '+res); 
                      return res;
                  })                
                //).subscribe((data) => console.log(data))
                .catch(this.handleError);
    return tmpParce;
  }

  // ---------------------------------- extractData --------------------------------------------   
  private extractData(res: Response) {
  let body = res.json();
  return body;//.data|| { }; //Промучился долго, не работал с моим json пока не закомментировал!!!
  }
  // ---------------------------------- getAcc --------------------------------------------
  getAcc(id: number): Observable<Acc> {
    let myParams: URLSearchParams = new URLSearchParams();
    myParams.append('userId', localStorage.getItem('currentUser'));
    let options = new RequestOptions({ headers: this.headers, params: myParams });
    const url = `${this.accUrl}/${id}`;
    return this.http.get(url, options)
                  .map(this.extractData)
                  .catch(this.handleError);
  } 

 // http.post для create
 // http.put  для update  
 // http.delete  для delete  

 // разбор файла. Имя файла передаем в url
 // так parseFile тоже работает, но не обновляется список образцов
//parseFile(ownerName: string): any {
  //const url = `${this.accUrl}/parse/${ownerName}`;
  //console.log(url);
  // так тоже работает
  //let xhr: XMLHttpRequest = new XMLHttpRequest();
  //xhr.open('GET', url, true);
  //xhr.send();
//}

// !!! НЕ исползуется. так deleteAccs тоже работает, но не обновляется список образцов. Теперь исползую delAccs
deleteAccs(ownerName: string): any {
  const url = `${this.accUrl}/del/${ownerName}`;
  //console.log(url);
  let xhr: XMLHttpRequest = new XMLHttpRequest();
  //'DELETE' не заработал xhr.open('DELETE', url, false);
  //Чтобы браузер передал вместе с запросом куки и HTTP-авторизацию
  xhr.withCredentials = true;
  xhr.open('GET', url, false);
  xhr.send();
}

// ---------------------------------- delAccs --------------------------------------------
delAccs(ownerName: string): Observable<string> { 
    let tmpParce: Observable<string>;
    const url = `${this.accUrl}/del/${ownerName}`;
    tmpParce = this.http.get(url)
                  .map((resp:Response)=>{
                      let res = resp.status;//.json();
                      console.log('res = '+res); 
                      return res;
                  })                
                //).subscribe((data) => console.log(data))
                .catch(this.handleError);
    return tmpParce;
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
  
}