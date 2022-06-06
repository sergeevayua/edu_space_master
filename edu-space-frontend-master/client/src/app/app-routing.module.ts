import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from '@app/services/interceptor.service';

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: 'login',
        loadChildren: () => import('./components/login/login.module').then(m => m.LoginModule),
      },
      {
        path: 'registration',
        loadChildren: () => import('./components/registration/registration.module').then(m => m.RegistrationModule),
      },
      {
        path: 'school-exams',
        loadChildren: () => import('./components/school-exams/school-exams.module').then(m => m.SchoolExamsModule),
      },
      {
        path: 'lk',
        loadChildren: () => import('./components/lk/lk.module').then(m => m.LkModule),
      },
      {
        path: 'it-jobs',
        loadChildren: () => import('./components/it-jobs/it-jobs.module').then(m => m.ItJobsModule),
      },
      {
        path: 'course-list',
        loadChildren: () => import('./components/course-list/course-lk.module').then(m => m.CourseLkModule),
      },
      {
        path: 'course-page',
        loadChildren: () => import('./components/course-page/course-page.module').then(m => m.CoursePageModule),
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'login'
      }
    ], {
      scrollPositionRestoration: 'top'
    })
  ],
  exports: [RouterModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ]
})
export class AppRoutingModule {

}
