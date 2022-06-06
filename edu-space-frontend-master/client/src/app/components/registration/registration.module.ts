import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationRoutingModule } from './registration-routing.module';
import { RegistrationComponent } from './registration.component';
import { SharedModule } from '../../modules/shared/shared.module';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    RegistrationRoutingModule,
    HttpClientModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [RegistrationComponent],
  exports: [RegistrationComponent]
})
export class RegistrationModule {
}
