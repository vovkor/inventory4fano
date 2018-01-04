import {Component, Input, OnInit} 	from '@angular/core';
import { Router, ActivatedRoute, Params} 	from '@angular/router';
import {Location}					from '@angular/common';
import {Acc} 						from './acc';
import {AccService}					from './acc.service';
import { GlobalDataService} 		from './globaldata.service';
//Also import the switchMap operator to use later with the route parameters Observable.
import 'rxjs/add/operator/switchMap';
// AccDetailComponent should take the id parameter from the params observable 
// in the ActivatedRoute service and use the AccService to fetch the acc with that id
@Component({
		moduleId: module.id,
		selector: 'my-acc-detail',
		templateUrl: './acc-detail.component.html',
})



export class AccDetailComponent implements OnInit{  //
	//@Input() // это декоратор. Он был нужен когда detail был дочерним компонетом
	//, теперь detail - отдельная страница и объект acc будем получать из 'id'
	acc: Acc; // property
	curPage: number; // с какой страницы перешли в detail
	// Let's have the ActivatedRoute service, the AccService and the Location service injected into 
	// the constructor, saving their values in private fields
	constructor(
  	private accService: AccService,
  	private route: ActivatedRoute,
	private router: Router,
  	private location: Location,
	private gd: GlobalDataService
	) {}	

	ngOnInit(): void {
		//use the AccService to fetch the acc with that id
		//The acc id is a number. Route parameters are always strings. 
		//So we convert the route parameter value to a number with the JavaScript (+) operator
		// чем отличается switchMap и subscribe. 
		// params property which is an Observable. To access the id, we have to subscribe to the parameters Observable changes
		// switchMap просто происходит раньше subscribe ???
	this.route.params
		//.switchMap((params: Params) => this.accService.getAcc(+params['id'])) // если в возвращаемым типом все понятно, то можно без return
		.switchMap((params: Params) => {this.curPage = +params['page']; return this.accService.getAcc(+params['id'])})
		.subscribe(acc => this.acc = acc);
	}

	goBack(): void {
		this.location.back();
	}



 	gotoAccs() {
		let accId = this.acc ? this.acc.id : null;
		let cPage = this.curPage ? this.curPage : 1;
		// Pass along the accession id if available
		// so that the accs.component can select that acc. Отработает ngOnInit()
		// Include a junk 'foo' property for fun.
		this.router.navigate(['/accs', { id: accId, page: cPage, foo: 'foo' }]);
 	}	
	
}