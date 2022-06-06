import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { DataService } from '../../services/data.service';
import { Logger } from '../../services/logger.service';

const log = new Logger('SchoolExamsComponent');

@Component({
  selector: 'app-school-exams',
  templateUrl: 'school-exams.component.html',
  styleUrls: ['school-exams.component.scss'],
})
export class SchoolExamsComponent implements OnInit, OnDestroy {
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
  ) {
  }

  ngOnDestroy(): void {
  }

  ngOnInit(): void {

  }

}
