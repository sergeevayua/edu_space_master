import { CdkTableModule } from '@angular/cdk/table';
import { CdkTreeModule } from '@angular/cdk/tree';
import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { RouterModule } from '@angular/router';
import { DataService } from '@app/services/data.service';
import { UtilsService } from '@app/services/utils.service';
import { ValidationService } from '@app/services/validation.service';
import { TranslateModule } from '@ngx-translate/core';
import { TruncateModule } from '@yellowspot/ng-truncate';
import { HighchartsChartModule } from 'highcharts-angular';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { CustomFormsModule } from 'ng2-validation';
import { MaterialModule } from '../../framework/material/material.module';
import { PipesModule } from '../pipes/pipes.module';
import { MatPaginatorI18nService } from './translations/mat-paginator-i18n.service';

/**
 * Do not specify providers for modules that might be imported by a lazy loaded module.
 */
@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    TranslateModule,
    MaterialModule,
    FormsModule,
    HighchartsChartModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    TruncateModule,
    CdkTableModule,
    CdkTreeModule,
    CustomFormsModule,
    PipesModule
  ],
  declarations: [],
  exports: [
    CommonModule,
    FormsModule,
    HighchartsChartModule,
    RouterModule,
    TranslateModule,
    PipesModule,
    TruncateModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    CdkTableModule,
    CdkTreeModule,
    CustomFormsModule
  ],
  providers: []
})
export class SharedModule {
  static forRoot(): ModuleWithProviders<SharedModule> {
    return {
      ngModule: SharedModule,
      providers: [
        { provide: MatPaginatorIntl, useClass: MatPaginatorI18nService },
        UtilsService,
        DataService,
        ValidationService
      ]
    };
  }
}
