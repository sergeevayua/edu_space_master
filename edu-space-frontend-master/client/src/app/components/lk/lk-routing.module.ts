import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LkComponent } from './lk.component';
import { AuthGuard } from '../../services/auth.guard';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: LkComponent, canActivate: [AuthGuard] }
    ])
  ],
  exports: [RouterModule]
})
export class LkRoutingModule {

}
