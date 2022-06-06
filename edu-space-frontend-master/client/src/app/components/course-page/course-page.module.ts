import { NgModule } from '@angular/core';
import { SharedModule } from '../../modules/shared/shared.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../framework/material/material.module';
import { CoursePageRoutingModule } from './course-page-routing.module';
import { CoursePageComponent } from './course-page.component';

@NgModule({
  imports: [
    CommonModule,
    CoursePageRoutingModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [
    CoursePageComponent
  ],
  exports: [
    CoursePageComponent
  ],
  entryComponents: [CoursePageComponent]
})
export class CoursePageModule {

}
