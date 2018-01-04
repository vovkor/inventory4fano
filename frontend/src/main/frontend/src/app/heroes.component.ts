import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
// Add the RxJS Observable operators we need in this app.
//import './rxjs-operators';
//import the Hero class from hero.ts
import { Hero } from './hero';
import { HeroService } from './hero.service';


@Component({
  moduleId: module.id,
  selector: 'my-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css'],
  providers: [HeroService]
})
export class HeroesComponent implements OnInit {
  // создадим a public property 
  title = 'Растительные генетические ресурсы.';
  heroes: Hero[];
  errorMessage: string;
  selectedHero: Hero;

  //!!! heroService = new HeroService(); // don't do this
  // Dependency Injection
  //Now Angular will know to supply an instance of the HeroService when it creates a new AppComponent
  // еще надо добавить providers и implements OnInit
  constructor(private router: Router, private heroService: HeroService) { }


  getHeroes(): void {
    // Promise's then method
    this.heroService.getHeroes().subscribe(
                     heroes => this.heroes = heroes,
                     error =>  this.errorMessage = <any>error);
  }

   ngOnInit(): void {
     this.getHeroes();
  }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedHero.id]);
  }
 
}
