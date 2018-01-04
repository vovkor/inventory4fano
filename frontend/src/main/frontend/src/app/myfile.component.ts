import { Component, OnInit, ViewContainerRef } from '@angular/core';

//import the MyFile class from myfile.ts
import { MyFile } from './myfile';
import { MyFileService } from './myfile.service';

// Создали компонент
// линк к этому компоненты добавляем в app.module.ts
@Component({
  moduleId: module.id,
  selector: 'my-file',
  templateUrl: './myfile.component.html',
  styleUrls: ['./myfile.component.css'],
  providers: [MyFileService]
})
export class MyFileComponent implements OnInit {
  errorMessage: string;
  myfiles: MyFile[] = [];
//•Inject the MyFileService in the constructor and hold it in a private myfileService field.
  constructor(private myFileService: MyFileService) { }

  ngOnInit() {this.getFiles();}

  getFiles() {
  this.myFileService.getFileByOwner( localStorage.getItem('currentUser'))
                   .subscribe(
                     myfiles => this.myfiles = myfiles,
                     error =>  this.errorMessage = <any>error);
}
 
/*
 ngOnInit(): void {
    this.heroService.getHeroes1().subscribe((data) => this.heroes = data);
  }
 */ 
}