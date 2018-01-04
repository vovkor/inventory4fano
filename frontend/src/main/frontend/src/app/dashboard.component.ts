import { Component, OnInit, ViewContainerRef } from '@angular/core';

//import the Hero class from hero.ts
import { Hero } from './hero';
import { HeroService } from './hero.service';

// Создали компонент
// линк к этому компоненты добавляем в app.module.ts
@Component({
  moduleId: module.id,
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [HeroService]
})
export class DashboardComponent implements OnInit {
  errorMessage: string;
  heroes: Hero[] = [];
//•Inject the HeroService in the constructor and hold it in a private heroService field.
  constructor(private heroService: HeroService) { }
/* для promise-варианта
  ngOnInit(): void {
    this.heroService.getHeroes_2ver()
      .then(heroes => this.heroes = heroes.slice(0)); // start 0; удаление по индексу
  }
*/
  ngOnInit() {this.getHeroes();}
  getHeroes() {
  this.heroService.getHeroes()
                   .subscribe(
                     heroes => this.heroes = heroes,
                     error =>  this.errorMessage = <any>error);
}
 
/*
 ngOnInit(): void {
    this.heroService.getHeroes1().subscribe((data) => this.heroes = data);
  }
 */ 
}