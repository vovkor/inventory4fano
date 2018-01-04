import { Component } from '@angular/core';
// Если добавляешь новые routerLink, то надо добавить в app-routing.module.ts
// чтобы можно было внедрить в AppComponent тот компонент, который обрабатывает запрос, необходимо использовать элемент RouterOutlet

// Создали компонент
// линк к этому компоненты добавляем в app.module.ts
@Component({
  moduleId: module.id,
  selector: 'fano-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class LogoComponent {
  title = 'Платформа для инвентаризации растительных генетических ресурсов.';
  private LOGO = require("./images/inventory_logo_sm.jpg");
}