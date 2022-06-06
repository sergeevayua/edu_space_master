import { NgModule } from '@angular/core';
import { SharedModule } from '../../modules/shared/shared.module';
import { CommonModule } from '@angular/common';
import { CourseLkComponent } from './course-lk.component';
import { CourseLkRoutingModule } from './course-lk-routing.module';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    CourseLkRoutingModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [
    CourseLkComponent
  ],
  exports: [
    CourseLkComponent
  ],
  entryComponents: [CourseLkComponent]
})
export class CourseLkModule {

}
