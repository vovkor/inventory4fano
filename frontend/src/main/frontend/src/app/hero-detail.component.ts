import {Component, Input, OnInit} 	from '@angular/core';
import {ActivatedRoute, Params} 	from '@angular/router';
import {Location}					from '@angular/common';
import {Hero} 						from './hero';
import {HeroService}				from './hero.service';
//Also import the switchMap operator to use later with the route parameters Observable.
import 'rxjs/add/operator/switchMap';
// HeroDetailComponent should take the id parameter from the params observable 
// in the ActivatedRoute service and use the HeroService to fetch the hero with that id
@Component({
		moduleId: module.id,
		selector: 'my-hero-detail',
		templateUrl: './hero-detail.component.html',
})



export class HeroDetailComponent implements OnInit{  //
	@Input() // это декоратор
	hero: Hero; // property
	// Let's have the ActivatedRoute service, the HeroService and the Location service injected into 
	// the constructor, saving their values in private fields
	constructor(
  	private heroService: HeroService,
  	private route: ActivatedRoute,
  	private location: Location
	) {}	

	ngOnInit(): void {
		//use the HeroService to fetch the hero with that id
		//The hero id is a number. Route parameters are always strings. 
		//So we convert the route parameter value to a number with the JavaScript (+) operator
	this.route.params
		.switchMap((params: Params) => this.heroService.getHero(+params['id']))
		.subscribe(hero => this.hero = hero);
	
	}
	goBack(): void {
	this.location.back();
	}	
	
}