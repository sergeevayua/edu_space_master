import { NgModule } from '@angular/core';
import { SharedModule } from '../../modules/shared/shared.module';
import { CommonModule } from '@angular/common';
import { ItJobsRoutingModule } from './it-jobs-routing.module';
import { ItJobsComponent } from './it-jobs.component';
import { ItJobsCourseComponent } from './it-jobs-course/it-jobs-course.component';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    ItJobsRoutingModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [
    ItJobsComponent,
    ItJobsCourseComponent
  ],
  exports: [
    ItJobsComponent,
    ItJobsCourseComponent
  ],
  entryComponents: []
})
export class ItJobsModule {

}
