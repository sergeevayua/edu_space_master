import { NgModule } from '@angular/core';
import { SharedModule } from '../../modules/shared/shared.module';
import { CommonModule } from '@angular/common';
import { SchoolExamsRoutingModule } from './school-exams-routing.module';
import { SchoolExamsComponent } from './school-exams.component';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    SchoolExamsRoutingModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [
    SchoolExamsComponent
  ]
})
export class SchoolExamsModule {

}
