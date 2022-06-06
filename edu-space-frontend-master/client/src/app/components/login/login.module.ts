import { ModuleWithProviders, NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../modules/shared/shared.module';
import { LoginRoutingModule } from './login-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    HttpClientModule,
    SharedModule,
    MaterialModule
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent]
})
export class LoginModule {
  static forRoot(): ModuleWithProviders<LoginModule> {
    return {
      ngModule: RootLoginModule,
      providers: []
    };
  }
}

@NgModule({
  imports: [
    LoginModule
  ],
})
export class RootLoginModule {
}
