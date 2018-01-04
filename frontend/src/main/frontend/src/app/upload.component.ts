import { Component} from '@angular/core';
import {UploadService} from './upload.service';
//http://stackoverflow.com/questions/39131790/file-upload-using-angularjs-2-and-asp-net-mvc-web-api
//http://stackoverflow.com/questions/43064548/uploading-file-with-other-form-fields-in-angular-2-and-spring-mvc
// также добавил код контроллер AccController.java
// работает вместе с C:\inventory4fano\backend\src\main\java\ru\nw\vir\storage

@Component({
  selector: 'upload-file',
  template: `
 <div class="row">
      <div class="col-md-3">
       {{spinner}}  
      </div>       
      <div class="col-md-9">
        <input class="btn btn-primary" type="file" accept="application/vnd.ms-excel" (change)="onChange($event)"/>  
      </div>       
 </div>  
 `,
  styleUrls: ['./accs.component.css'],
  providers: [ UploadService ]
 })
  export class UploadComponent {
   picName: string;
   spinner: string;
   constructor(private service:UploadService) {
    this.spinner = "";
    this.service.progress$.subscribe(
     data => {
      //console.log('progress = '+data);
      this.spinner = 'Загрузка '+data+'%';
      });
    }

     onChange(event) {
            //console.log('onChange');
         var files = event.srcElement.files;
            //console.log(files);
         this.spinner = "Загрузка...";   
         this.service.makeFileRequest('http://91.151.189.38:8080/upload', [], files).subscribe(() => {
            //console.log('sent');
        //this.picName = fileName;
     });
     }
}
