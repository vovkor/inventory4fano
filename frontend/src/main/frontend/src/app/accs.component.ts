//для Pagination поставил ngx-bootstrap
//https://www.npmjs.com/package/ng2-bootstrap   http://valor-software.com/ngx-bootstrap/#/pagination 

// пример фильтра, сортировки http://valor-software.com/ng2-table/

import { Component, OnInit, ViewContainerRef} from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
// Add the RxJS Observable operators we need in this app.
//import './rxjs-operators';
//import the Acc class from acc.ts
import { Acc } from './acc';
import { AccService }   from './acc.service';
import { Location }		  from '@angular/common';
import { GlobalDataService} from './globaldata.service';

//для Pagination
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { Subscription }     from 'rxjs/Subscription';

import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';


@Component({
  moduleId: module.id,
  selector: 'my-accs',
  templateUrl: './accs.component.html',
  styleUrls: ['./accs.component.css'],
  providers: [AccService]
})

export class AccsComponent implements OnInit {
  // создадим a public property 
 
  title = 'Растительные генетические ресурсы.';
  accs1: Observable<Acc[]>;
  accs: Acc[];
  errorMessage: string;
  selectedAcc: Acc;
  countAccStr: string = 'Количество:';
  subscriptionCount: Subscription;
  result: string;
  userName: string;
  countRec: string;

  //настройка для Pagination
  public totalItems: number;// = 0;
  public currentPage: number = 1;
  public smallnumPages: number = 0;
  public ItemsPerPage = 100;
  public nextTextRus = '>';
  public previousTextRus = '<';
  public firstTextRus = '<<';
  public lastTextRus = '>>';  

  private selectedId: number;

  
  //!!! accService = new AccService(); // don't do this
  // Dependency Injection
  //Now Angular will know to supply an instance of the AccService when it creates a new AppComponent
  // еще надо добавить providers и implements OnInit
  constructor(private router: Router, private route: ActivatedRoute, private accService: AccService, private location: Location, private gd: GlobalDataService )
   {
    console.log('constructor');
    this.gd.shareObj['userId']='для глобальных переменных';
    // subscribe принимает 3 параметра
    //.subscribe(success, failure, complete);
    //.subscribe(
    //function(response) { console.log("Success Response" + response)},
    //function(error) { console.log("Error happened" + error)},
    //function() { console.log("the subscription is completed")}
    //);
    this.subscriptionCount = accService.totalCount$.subscribe(
        value => {
          this.totalItems = value;
          //console.log('subscribe: '+this.totalItems);  
        }); 
   }


  getAccs(page?: number, itemsPerPage?: number): void {
    // Promise's then method
    // Observable.subscribe возвращает объект типа Subscription, а не другой Observable
    this.accService.getAccs(page, itemsPerPage, this.countRec).subscribe(
                     (accs) => {this.accs = accs; console.log('Вызывал getAccs');
                                console.log(' this.currentPage = '+ this.currentPage);
                               },
                     error =>  this.errorMessage = <any>error);
  }

  getParce(ownerName: string): void {
    // не знаем сколько длится парсинг, поэтому подписываемся на наблюдаемую функцию parseFile
    // => перед { 
    //arrow functions in your code to be able to use the "contextual this"
    this.accService.getParce(ownerName).subscribe(
                     (response) => { console.log("Success Response " + response);
                    },
                     error =>  this.errorMessage = <any>error,
                     () => { console.log("the subscription is completed");
                              this.getAccs(1, this.ItemsPerPage); // обновим датасет!!! :-)
                            //this.router.navigate(['/acc']);  // может навигация поможет обновить данные? НЕТ
                           }
                     );
  }
  
  // парсинг Excel-файла
  save(): void {
    this.getParce(this.userName); 
    console.log('Save: ' + this.userName);
  }

  delAcc(ownerName: string): void {
    // не знаем сколько длится удаление, поэтому подписываемся на наблюдаемую функцию deleteAccs
    // => перед { 
    //arrow functions in your code to be able to use the "contextual this"
    this.accService.delAccs(ownerName).subscribe(
                     (response) => { console.log("Success delete " + response);
                    },
                     error =>  this.errorMessage = <any>error,
                     () => { console.log("the subscription is completed");
                              this.getAccs(1, 0); // обновим датасет!!! :-)
                           }
                     );
  }

  // удалить записи
  deleteAccessions(): void {
    this.delAcc(this.userName);
  }

  // при изменении поля "Количество записей"
  editCountRec(): void {
    localStorage.setItem('countRec', this.countRec);
    this.getAccs(this.currentPage, this.ItemsPerPage);
  }

//Для получения параметров маршрута нам необходим специальный сервис ActivatedRoute.
//Он содержит информацию о маршруте, в частности, параметры маршрута,
//параметры строки запроса и прочее. 
//Он внедряется в приложение через механизм dependency injection, поэтому в конструкторе мы можем получить его.

 ngOnInit(): void {
  
  //this.totalItems = this.accService.totalItems;
  //console.log('ngOnInit'+this.totalItems); 
  console.log('-- ngOnInit -- '); 
  this.userName = localStorage.getItem('currentUser');
  this.countRec = localStorage.getItem('countRec');
  if  (this.countRec == undefined) // is NULL
  {this.countRec = '200'}

 
  // при возврате список из detail должна выделиться запись с которой произошел переход в detail
  // и выбраться правильная страница.
  // код из официальной доки не заработал https://angular.io/docs/ts/latest/guide/router.html#!#add-login-component
  // заработал код из https://metanit.com/web/angular2/7.3.php
  // видимо потому что getAccs возвращает Observable<Acc[]>, а у меня this.accs типа Acc[]
  // но я делал свойство accs1 Observable<Acc[]> и до console.log('роут'); не доходит
  /*
  this.accs1 = this.route.params
       .switchMap((params: Params) => {
       //this.selectedId = +params['id'];
       this.currentPage = +params['page'];
       this.setPage(this.currentPage); // не помогло выставить страницу
       console.log('роут');
       return this.accService.getAccs((this.currentPage, this.ItemsPerPage));
      });
  */
  this.route.params.subscribe(params=>{
                                        this.selectedId = +params['id'];
                                        this.currentPage = +params['page'];
                                        console.log(' this.currentPage = '+ this.currentPage);
                                        this.currentPage = this.currentPage ? this.currentPage : 1;
                                        this.getAccs(this.currentPage, this.ItemsPerPage);                                             
                                        
                                        
                                      }
                             );
   } // ngOnInit()

    ngOnChanges() {
      
      console.log("OnChanges");
    }


  isSelected(acc: Acc) { return acc.id === this.selectedId; }

  // Вызывается после переключения страниц pagination
  buildPageContents(event: any): void {
    console.log('Page changed to: ' + event.page);
    this.getAccs(event.page, this.ItemsPerPage);
    //this.totalItems = this.accService.totalItems;
  }
  onSelect(acc: Acc): void {
   this.selectedAcc = acc;
   this.selectedId = acc.id; // нужен для выделения цветом кликнутой записи
  }

  gotoDetail(): void {
    let cPage = this.currentPage ? this.currentPage : 1;
    this.router.navigate(['/accdetail', this.selectedAcc.id, { page: cPage }]);
  }

	goBack(): void {
	this.location.back();
	}	

  public setPage(pageNo: number): void {
    console.log('setPage: ' + pageNo);
    this.currentPage = pageNo;
  }

  public pageChanged(event: any): void {
    console.log('pageChanged: ' + event.page);
    //console.log('Number items per page: ' + event.itemsPerPage);
  }
  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscriptionCount.unsubscribe();
  }

  checkUsername(): string{
    return localStorage.getItem('currentUser');
  
}

  //http://stackoverflow.com/questions/43064548/uploading-file-with-other-form-fields-in-angular-2-and-spring-mvc
 /*
  fileChange(e){
    this.fileList = e.target.files;
  }

uploadPdf(){
    if(this.fileList.length>0){
      let file: File = this.fileList[0];
      let formData:FormData = new FormData();

      formData.append('uploadFile', file, file.name);
      formData.append('info',this.model);

      console.log(file.size);

      let headers = new Headers();
      headers.append('Accept', 'application/json');
      let options = new RequestOptions({ headers: headers });
      this.http.post(this.url,formData, options)
       
        .catch(error => Observable.throw(error))
        .subscribe(
          data =>{
            this.data = data;
            console.log(this.data);
          }
          ,
          error => console.log(error)
        )
    }
  }

*/
 
}
