import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import * as Highcharts from 'highcharts';
// logger
import { Logger } from '@app/services/logger.service';
import { DataService } from '../../services/data.service';
import { Subject } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs/operators';
import { optFromInput } from './lk-highcharts.config';

const log = new Logger('LkComponent');

@Component({
  selector: 'app-lk',
  templateUrl: 'lk.component.html',
  styleUrls: ['lk.component.scss'],
})
export class LkComponent implements OnInit, OnDestroy {
  destroy$: Subject<boolean> = new Subject<boolean>();
  chartLoading: boolean;
  chart: Highcharts.Chart;
  charts = [];
  Highcharts: typeof Highcharts = Highcharts;
  chartConstructor = 'chart'; // optional string, defaults to 'chart' stockChart
  updateFromInput = false;
  optFromInput: Highcharts.Options = {};
  noDataMessage: any;
  isInit = false;

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
    private _snackBar: MatSnackBar,
    private cdRef: ChangeDetectorRef,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    Object.assign(this, {
      optFromInput
    });
  }

  chartCallback: Highcharts.ChartCallbackFunction = (chart) => {
    this.chart = chart;
    this.chart.showLoading();
    this.dataService.getAggregatedDataForChart(1)
      .pipe(take(1))
      .subscribe(response => {
        log.debug('chart data', response);
        const chartData = response.data.map(item => {
          item[1] = Number(item[1]);
          return item;
        });
        log.debug('chart data new', chartData);
        this.charts = [{
          name: 'Кол-во курсов',
          data: chartData
        }];
        this.drawChart(this.charts);
      });
  };

  private drawChart(response: any): void {
    log.debug('resps', response);
    this.optFromInput.series = response;
    this.chart.hideLoading();
    this.chart.redraw(true);
    this.updateFromInput = true;
    this.cdRef.markForCheck();
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
    this.optFromInput.series = [];
    this.optFromInput.annotations = [];
  }
}
