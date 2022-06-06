import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { DataService } from '../../services/data.service';
import { Logger } from '../../services/logger.service';
import { Router } from '@angular/router';
import { finalize, takeUntil } from 'rxjs/operators';
import { CourseGroup } from '../../framework/interfaces/courseGroup';
import { links } from '../../nav-links';
import { Store } from '@ngrx/store';
import * as fromRoot from '@app/modules/store/app.reducers';

const log = new Logger('ItJobsComponent');

@Component({
  selector: 'app-it-jobs',
  templateUrl: 'it-jobs.component.html',
  styleUrls: ['it-jobs.component.scss'],
})
export class ItJobsComponent implements OnInit, OnDestroy, AfterViewInit {
  destroy$: Subject<boolean> = new Subject<boolean>();
  coursesGroupArray: any[];
  professionId: number = 1;
  loading: boolean;
  activeRoute: any;

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
    public router: Router,
    private cdRef: ChangeDetectorRef,
    private store: Store<fromRoot.State>,
  ) {
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit(): void {
  }

  getAllCoursesGroupsByProfessionId(id: any) {
    this.dataService.getAllCoursesGroupsByProfessionId(id)
      .pipe(
        finalize(() => {

          this.cdRef.markForCheck();
        })
      )
      .subscribe(response => {
        log.debug('coursegroup', response);
        this.coursesGroupArray = response;
      })
  }

  getAllCoursesByGroupId(id: any) {
    this.dataService.getAllCoursesByGroupId(id)
      .pipe(
        finalize(() => {
          this.cdRef.markForCheck();
        })
      )
      .subscribe(response => {
        log.debug('resp', response);
      })
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      links[0].items.forEach(item => {
        if (item.link === this.router.url.replace('/', '')) {
          this.professionId = item.id;
        }
      });
      this.getAllCoursesGroupsByProfessionId(this.professionId);
    }, 50);
  }

}
