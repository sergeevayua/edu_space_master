import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../../services/auth.guard';
import { CoursePageComponent } from './course-page.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: CoursePageComponent, canActivate: [AuthGuard] },
    ])
  ],
  exports: [RouterModule]
})
export class CoursePageRoutingModule {

}
