import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { DataService } from '../../services/data.service';
import { Logger } from '../../services/logger.service';
import { Router } from '@angular/router';
import { finalize, take, takeUntil } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import * as fromRoot from '@app/modules/store/app.reducers';

const log = new Logger('CourseLkComponent');

@Component({
  selector: 'app-course-lk',
  templateUrl: './course-lk.component.html',
  styleUrls: ['./course-lk.component.scss']
})
export class CourseLkComponent implements OnInit, OnDestroy, AfterViewInit {
  destroy$: Subject<boolean> = new Subject<boolean>();
  coursesGroupArray: any[];
  professionId: string;
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
    this.store.select(fromRoot.getRouterState)
      .pipe(
        takeUntil(this.destroy$),
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe((data: any) => {
        this.activeRoute = data;
        this.professionId = this.activeRoute.params.group_id;
      });
  }

  getAllCoursesByUserId() {
    this.dataService.getAllCoursesByUserId(1)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('coursegroup', response);
        this.coursesGroupArray = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
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
      this.getAllCoursesByUserId();
    }, 50);
  }

}
