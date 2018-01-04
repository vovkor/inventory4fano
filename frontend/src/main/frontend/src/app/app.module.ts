// RouterModule.  Routes are an array of route definitions
// the router displays each component immediately below the <router-outlet>
import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms'; // <-- NgModel lives here
// !!! ------- добавил новый компонент, добававь сюда import и в declarations ------ !!!
import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { HeroDetailComponent }  from './hero-detail.component';
import { HeroesComponent }      from './heroes.component';
import { HeroService }          from './hero.service';

import { AccsComponent }       from './accs.component';
import { AccDetailComponent }  from './acc-detail.component';
import { AccChildComponent }   from './acc-child.component';
import { AccService }          from './acc.service';

import { UploadComponent }          from './upload.component';
import { UploadService }            from './upload.service';
import { GlobalDataService }        from './globaldata.service';

import { AppRoutingModule }         from './app-routing.module';
// Для get post запросов
import { HttpModule, JsonpModule }  from '@angular/http';
//для Pagination
import { PaginationModule }         from 'ngx-bootstrap/pagination';

//import { AdminModule }              from './admin/admin.module';
import { PageNotFoundComponent }    from './not-found.component';
// в доке этого нет, иначе ошибка "No provider for AuthGuard!"
//import { AuthGuard }                from './auth-guard.service';
// в доке этого нет, иначе ошибка "No provider for AuthService!"
import { AuthService }          from './auth.service';

import { LoginRoutingModule }      from './login-routing.module';
import { LoginComponent }          from './login.component';
import { LogoComponent }          from './logo.component';
import { MyFileComponent }          from './myfile.component';
import { MyFileService }          from './myfile.service';
import { Router } from '@angular/router';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
   // AdminModule,
  
    PaginationModule.forRoot(),
    LoginRoutingModule,
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HeroDetailComponent,
    HeroesComponent,
    AccsComponent,
    AccDetailComponent,
    AccChildComponent,
    UploadComponent,
    LoginComponent,
    PageNotFoundComponent,
    LogoComponent,
    MyFileComponent
  ],
  providers: [ HeroService,
               AccService,
               UploadService,
               GlobalDataService,
               AuthService,
               MyFileService,
            //   AuthGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}