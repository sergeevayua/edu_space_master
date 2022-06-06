import { NgModule } from '@angular/core';
import { SharedModule } from '../../modules/shared/shared.module';
import { CommonModule } from '@angular/common';
import { LkRoutingModule } from './lk-routing.module';
import { LkComponent } from './lk.component';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    LkRoutingModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [
    LkComponent
  ]
})
export class LkModule {

}
