import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../../services/auth.guard';
import { ItJobsComponent } from './it-jobs.component';
import { ItJobsCourseComponent } from './it-jobs-course/it-jobs-course.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: ItJobsComponent, canActivate: [AuthGuard] },
      {
        path: 'it-course/:course_id',
        component: ItJobsCourseComponent,
        canActivate: [AuthGuard],
        data: { key: 'it-course' }
      }
    ])
  ],
  exports: [RouterModule]
})
export class ItJobsRoutingModule {

}
