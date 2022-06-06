import { NgModule } from '@angular/core';
import { DateFormatPipe } from './dateFormatPipe';
import { SecurePipe } from './securePipe';
import { PermissionsPipe } from './permissions.pipe';

@NgModule({
  imports: [],
  declarations: [
    DateFormatPipe,
    SecurePipe,
    PermissionsPipe
  ],
  exports: [
    DateFormatPipe,
    SecurePipe,
    PermissionsPipe
  ]
})
export class PipesModule {
}
