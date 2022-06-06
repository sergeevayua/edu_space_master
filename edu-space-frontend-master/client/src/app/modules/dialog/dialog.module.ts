import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PipesModule } from '../pipes/pipes.module';
import { SharedModule } from '../shared/shared.module';
// services
import { DialogService } from './dialog.service';

// components
import { ConfirmationComponent } from './confirmation/confirmationDialog.component';
import { MaterialModule } from '../../framework/material/material.module';

@NgModule({
  imports: [
    PipesModule,
    CommonModule,
    SharedModule,
    FormsModule,
    MaterialModule
  ],
  declarations: [
    ConfirmationComponent,
  ],
  exports: [
    ConfirmationComponent,
  ],
  entryComponents: [
    ConfirmationComponent,
  ],
  providers: [
    DialogService
  ]
})
export class DialogModule {
}
