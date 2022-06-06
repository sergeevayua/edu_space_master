import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../../services/auth.guard';
import { SchoolExamsComponent } from './school-exams.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: SchoolExamsComponent, canActivate: [AuthGuard] }
    ])
  ],
  exports: [RouterModule]
})
export class SchoolExamsRoutingModule {

}
