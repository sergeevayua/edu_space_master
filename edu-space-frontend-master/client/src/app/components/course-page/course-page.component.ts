import { ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DataService } from '../../services/data.service';
import { Logger } from '../../services/logger.service';
import { take } from 'rxjs/operators';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { UtilsService } from '../../services/utils.service';
import { AuthService } from '../../services/auth.service';

const log = new Logger('EditPageComponent');

interface MappedModel {
  value: number;
  label: string;
}

@Component({
  selector: 'app-edit-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.scss']
})
export class CoursePageComponent implements OnInit {

  @ViewChild('courseGroupsTemplate')
  public courseGroupsTemplate: TemplateRef<HTMLElement>;

  @ViewChild('coursesTemplate')
  public coursesTemplate: TemplateRef<HTMLElement>;

  formCourseGroup: FormGroup;
  formCourse: FormGroup;
  formModule: FormGroup;
  formChapter: FormGroup;
  formExercise: FormGroup;
  professions: MappedModel[];
  courseGroups: MappedModel[] = [];
  courses: MappedModel[] = [];
  modules: MappedModel[] = [];
  chapters: MappedModel[] = [];
  activeArray: boolean[] = [];
  currentProfessionalId: number;
  currentCourseGroupId: number;
  currentCourseId: number;
  currentTemplate: string = '';
  currentModel: MappedModel | null;
  exercises: MappedModel[] = [];

  constructor(
    private fb: FormBuilder,
    private dataService: DataService,
    private cdRef: ChangeDetectorRef,
    private authService: AuthService,
    private utils: UtilsService
  ) {
    this.formCourseGroup = this.fb.group({
      name: ['']
    });
    this.formCourse = this.fb.group({
      name: ['']
    });
    this.formModule = this.fb.group({
      name: ['']
    });
    this.formChapter = this.fb.group({
      name: ['']
    });
    this.formExercise = this.fb.group({
      name: [''],
      numeration: [''],
      description: [''],
      content: ['']
    });
  }

  public ngOnInit(): void {
    this.getProfessions();
  }

  public changeTab(event: MatTabChangeEvent): void {
    const profId = event.index + 1;
    this.getCourseGroupsByProfession(profId);
    this.currentProfessionalId = profId;
  }

  public showTemplate(parent: string = '', currentModel?: MappedModel): void {
    this.currentTemplate = parent;
    this.currentModel = currentModel;
    switch (parent) {
      case ('courseGroups'):
        this.getCoursesByGroup(currentModel.value);
        this.currentCourseGroupId = currentModel.value;
        break;
      case ('courses'):
        this.getModulesByCourse(this.currentCourseGroupId, currentModel.value);
        this.currentCourseId = currentModel.value;
        break;
      case ('modules'):
        this.getChaptersByModule(currentModel.value);
        break;
      case ('chapters'):
        this.getExercisesByChapter(currentModel.value);
        break;
    }
  }

  public getProfessions(): void {
    this.dataService.getAllProfessions()
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('professions', response);
        this.professions = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public getCourseGroupsByProfession(profId: number): void {
    this.dataService.getAllCoursesGroupsByProfessionId(profId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('courseGroups', response);
        this.courseGroups = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public getCoursesByGroup(groupId: number): void {
    this.dataService.getAllCoursesByGroupId(groupId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('courses', response);
        this.courses = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public getModulesByCourse(groupId, courseId: number): void {
    this.dataService.getAllModulesByCourseId(groupId, courseId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('modules', response);
        this.modules = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public getChaptersByModule(moduleId: number): void {
    this.dataService.getAllChaptersByModuleId(moduleId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('chapters', response);
        this.chapters = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public getExercisesByChapter(chapterId: number): void {
    this.dataService.getAllExercisesByChapterId(chapterId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('exercises', response);
        this.exercises = response.map(item => {
          return { value: item.id, label: item.name };
        });
        this.cdRef.markForCheck();
      });
  }

  public saveCourseGroup(): void {
    const body = {
      name: this.formCourseGroup.value.name
    };
    this.dataService.createCourseGroup(body, this.currentProfessionalId)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.getCourseGroupsByProfession(this.currentProfessionalId);
        this.formCourseGroup.controls.name.reset('');
      });
  }

  public saveCourse(): void {
    const body = {
      name: this.formCourse.value.name,
    };
    this.dataService.createCourse(body, this.currentModel.value)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.getCoursesByGroup(this.currentModel.value);
        this.formCourse.controls.name.reset('');
      });
  }

  public saveModule(): void {
    const body = {
      name: this.formModule.value.name
    };
    this.dataService.createModule(body, this.currentCourseGroupId, this.currentModel.value)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.getModulesByCourse(this.currentCourseGroupId, this.currentModel.value);
        this.formModule.controls.name.reset('');
      });
  }

  public saveChapter(): void {
    const body = {
      name: this.formChapter.value.name
    };
    this.dataService.createChapter(body, this.currentModel.value)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.getChaptersByModule(this.currentModel.value);
        this.formChapter.controls.name.reset('');
      });
  }

  public saveExercise(): void {
    const body = {
      name: this.formExercise.value.name,
      numeration: this.formExercise.value.numeration,
      description: this.formExercise.value.description,
      content: this.formExercise.value.content
    };
    this.dataService.createExercise(body, this.currentModel.value)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.getExercisesByChapter(this.currentModel.value);
        this.formExercise.controls.name.reset('');
        this.formExercise.controls.numeration.reset('');
        this.formExercise.controls.description.reset('');
        this.formExercise.controls.content.reset('');
      });
  }

  public editCourseGroup(id, cgi: number): void {
    const body = {
      name: this.formCourseGroup.value.name
    };
    this.dataService.updateCourseGroup(body, this.currentProfessionalId, id)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.activeArray[cgi] = !this.activeArray[cgi];
        this.getCourseGroupsByProfession(this.currentProfessionalId);
      });
  }

  public editCourse(id, cgi: number): void {
    const body = {
      name: this.formCourse.value.name
    };
    this.dataService.updateCourse(body, this.currentModel.value, id)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.activeArray[cgi] = !this.activeArray[cgi];
        this.getCoursesByGroup(this.currentModel.value);
      });
  }

  public editModule(id, cgi: number): void {
    const body = {
      name: this.formModule.value.name
    };
    this.dataService.updateModule(body, this.currentCourseGroupId, this.currentModel.value, id)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.activeArray[cgi] = !this.activeArray[cgi];
        this.getModulesByCourse(this.currentCourseGroupId, this.currentModel.value);
      });
  }

  public editChapter(id, cgi: number): void {
    const body = {
      name: this.formChapter.value.name
    };
    this.dataService.updateChapter(body, this.currentModel.value, id)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.activeArray[cgi] = !this.activeArray[cgi];
        this.getChaptersByModule(this.currentModel.value);
      });
  }

  public editExercise(id, cgi: number): void {
    const body = {
      name: this.formExercise.value.name
    };
    this.dataService.updateExercise(body, this.currentModel.value, id)
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('reps', response);
        this.activeArray[cgi] = !this.activeArray[cgi];
        this.getExercisesByChapter(this.currentModel.value);
      });
  }

  public checkPermission(permission): boolean {
    return this.authService.getPermissions().includes(permission);
  }
}
