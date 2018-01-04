import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard.component';
import { HeroesComponent }      from './heroes.component';
import { HeroDetailComponent }  from './hero-detail.component';

import { AccsComponent }        from './accs.component';
import { AccDetailComponent }   from './acc-detail.component';

import { AuthGuard }            from './auth-guard.service';
import { PageNotFoundComponent } from './not-found.component';
import { SelectivePreloadingStrategy } from './selective-preloading-strategy';
// временно добавил
import { LoginComponent }          from './login.component';

import { LogoComponent }          from './logo.component';
import { MyFileComponent }          from './myfile.component';


const routes: Routes = [
  //{ path: '', redirectTo: '/acc', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'heroes',     component: HeroesComponent },
  { path: 'acc',        component: AccsComponent, canActivate: [AuthGuard] },
  { path: 'accs',        component: AccsComponent, canActivate: [AuthGuard] },
  { path: 'accdetail/:id', component: AccDetailComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'myfile', component: MyFileComponent },
  { path: '', component: LogoComponent },
   
 {
    path: '', // было пусто
    loadChildren: 'app/admin/admin.module#AdminModule'//,
    //canLoad: [AuthGuard] с ним НЕ работает!
  },
  
 { path: '**', component: PageNotFoundComponent },

];
// в доке  AuthGuard нет в разделе providers, иначе ошибка "No provider for AuthGuard!"
@NgModule({
  imports:[  RouterModule.forRoot(routes, { preloadingStrategy: SelectivePreloadingStrategy })] ,
  exports: [ RouterModule ],
  providers: [
    // в доке этого нет, иначе ошибка "No provider for AuthGuard!"
   // AuthGuard,
    SelectivePreloadingStrategy
  ]  
})
export class AppRoutingModule {}