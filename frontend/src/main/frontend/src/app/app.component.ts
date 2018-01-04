import { Component } from '@angular/core';
// Если добавляешь новые routerLink, то надо добавить в app-routing.module.ts
// чтобы можно было внедрить в AppComponent тот компонент, который обрабатывает запрос, необходимо использовать элемент RouterOutlet

@Component({
  selector: 'app-root',
  template: `
  <!-- <h1>{{title}}</h1> -->
  <nav>
    <a routerLink="/dashboard" routerLinkActive="active">Статистика</a>
    <a routerLink="/myfile" routerLinkActive="active">Файлы</a>
    <!-- <a routerLink="/heroes" routerLinkActive="active">Список</a> -->
    <a routerLink="/acc" routerLinkActive="active">Список образцов</a>
    <!-- <a routerLink="/admin" routerLinkActive="active">Админ</a> -->
    <a routerLink="/login" routerLinkActive="active">Вход</a>
  </nav>
  <router-outlet></router-outlet>
  
`,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Всероссийская инвентаризация.';
}