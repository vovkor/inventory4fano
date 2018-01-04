// добавил новый компонент, добававь в app.module.ts
import {Component, Input, OnInit} 	from '@angular/core';
import {Location}					from '@angular/common';
import {Acc} 						from './acc';

@Component({
	selector: 'acc-child',
    template: `
    <h3>{{acc.accenumb}} says:</h3>
    <p>I, {{acc.accename}}, am at your service, {{countAccTotal}}.</p>
  `
})



export class AccChildComponent{  
	@Input() acc: Acc; // property
	@Input('countAcc') countAccTotal: string;
	// Let's have the ActivatedRoute service, the AccService and the Location service injected into 
	// the constructor, saving their values in private fields
	
}