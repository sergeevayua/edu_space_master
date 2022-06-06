import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../../services/auth.guard';
import { CourseLkComponent } from './course-lk.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: CourseLkComponent, canActivate: [AuthGuard] }
    ])
  ],
  exports: [RouterModule]
})
export class CourseLkRoutingModule {

}
