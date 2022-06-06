import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DataService } from '../../../services/data.service';
import { Router } from '@angular/router';
import { finalize, takeUntil } from 'rxjs/operators';
import { Logger } from '../../../services/logger.service';
import * as fromRoot from '@app/modules/store/app.reducers';
import { Course } from '../../../framework/interfaces/course';
import { Store } from '@ngrx/store';
import { Subject } from 'rxjs';
import { NestedTreeControl } from '@angular/cdk/tree';
import { Module } from '../../../framework/interfaces/module';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { Exercise } from '../../../framework/interfaces/exercise';

const log = new Logger('ItJobsCourseComponent');

@Component({
  selector: 'app-it-jobs-course',
  templateUrl: 'it-jobs-course.component.html',
  styleUrls: ['it-jobs-course.component.scss'],
})
export class ItJobsCourseComponent implements OnInit, OnDestroy, AfterViewInit {
  destroy$: Subject<boolean> = new Subject<boolean>();
  course: Course;
  loading: boolean;
  courseId: string;
  courseGroupId: string;
  exercisesArray: Array<Exercise>;
  activeRoute: any;
  treeControl = new NestedTreeControl<Module>(node => node.chapters);
  dataSource = new MatTreeNestedDataSource<Module>();
  rightArea = false;

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
    public router: Router,
    private cdRef: ChangeDetectorRef,
    private store: Store<fromRoot.State>,
  ) {
  }

  hasChild = (_: number, node: Module) => !!node.chapters && node.chapters.length > 0;

  ngAfterViewInit(): void {
    this.getCourseByGroupId(this.courseGroupId, this.courseId);
    this.getModulesByGroupAndCourseId(this.courseGroupId, this.courseId);
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
          this.cdRef.markForCheck();
        })
      )
      .subscribe((data: any) => {
        this.activeRoute = data;
        this.courseId = this.activeRoute.params.course_id;
        this.courseGroupId = this.activeRoute.params.group_id;
      });
  }

  getCourseByGroupId(courseGroupId, courseId: any) {
    this.dataService.getCourseByCourseId(courseGroupId, courseId)
      .pipe(
        finalize(() => {
          this.cdRef.markForCheck();
        })
      )
      .subscribe(response => {
        log.debug('course', response);
        this.course = response;
      })
  }

  getModulesByGroupAndCourseId(courseGroupId, courseId: any) {
    this.dataService.getAllModulesByCourseId(courseGroupId, courseId)
      .pipe(
        finalize(() => {
          this.getExercisesByChapterId(this.course.modules[0].chapters[0].id);
          this.cdRef.markForCheck();
        })
      )
      .subscribe(response => {
        this.course.modules = response;
        this.dataSource.data = this.course.modules;
        log.debug('modules', this.course);
      })
  }

  getExercisesByChapterId(chapterId: any) {
    this.dataService.getAllExercisesByChapterId(chapterId)
      .pipe(
        finalize(() => {
          this.cdRef.markForCheck();
        })
      )
      .subscribe(response => {
        log.debug('exercises', response);
        this.exercisesArray = response;
      })
  }

}
