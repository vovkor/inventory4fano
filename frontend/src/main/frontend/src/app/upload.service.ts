//http://stackoverflow.com/questions/39131790/file-upload-using-angularjs-2-and-asp-net-mvc-web-api
//http://stackoverflow.com/questions/43064548/uploading-file-with-other-form-fields-in-angular-2-and-spring-mvc
// также добавил код контроллер AccController.java
// работает вместе с C:\inventory4fano\backend\src\main\java\ru\nw\vir\storage
import {Injectable}from '@angular/core';
import {Observable} from 'rxjs/Rx';
@Injectable()
export class UploadService {
progress$: any;
progress: any;
progressObserver: any;
constructor() {
    this.progress$ = Observable.create(observer => {
        this.progressObserver = observer
    }).share();
}
//"uploads[]"
 makeFileRequest(url: string, params: string[], files: File[]): Observable<any> {
    return Observable.create(observer => {
        let formData: FormData = new FormData(),
            xhr: XMLHttpRequest = new XMLHttpRequest();


          
        for (let i = 0; i < files.length; i++) {
            formData.append("uploadFile", files[i], files[i].name);
        }
        // пользователь
        formData.append("userId", localStorage.getItem('currentUser'));
        
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                   // observer.next(JSON.parse(xhr.response)); ожидаем JSON Ответ???
                    observer.complete();
                } else {
                    observer.error(xhr.response);
                }
            }
        };

        xhr.upload.onprogress = (event) => {
            this.progress = Math.round(event.loaded / event.total * 100);

            this.progressObserver.next(this.progress);
        };

        //open( method, URL, async, userName, password )
        xhr.open('POST', url, true); //если установлено в false, то запрос производится синхронно, если true – асинхронно.
        //xhr.timeout = 3000;
        
        var serverFileName = xhr.send(formData);
        return serverFileName;
    });
}
}
