import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PlatformModule } from '@angular/cdk/platform';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

// env
import { Configuration } from '@conf/env.config';
import { OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';

// libs
import { RouterStateSerializer } from '@ngrx/router-store';
import { CustomRouterStateSerializer } from '@app/framework/utils/custom-router-state-serializer';
import { ConfigLoader, ConfigModule } from '@ngx-config/core';
import { ConfigHttpLoader } from '@ngx-config/http-loader';
import { TranslateLoader } from '@ngx-translate/core';
// framework
import { AngularResizedEventModule } from 'angular-resize-event';

// routes & components
import { SharedModule } from './modules/shared/shared.module';

// feature modules
import { AppStoreModule } from '@app/modules/store/app-store.module';
import { HttpLoaderFactory, Languages, MultilingualModule } from '@app/modules/i18n/index';
import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import { ToolbarComponent } from '@app/modules/shared/toolbar/toolbar.component';
import { Logger } from '@app/services/logger.service';
import { MaterialModule } from './framework/material/material.module';
import { DialogModule } from '@app/modules/dialog/dialog.module';

// for AoT compilation
function configFactory(http: HttpClient): ConfigLoader {
  return new ConfigHttpLoader(http, './assets/config.local.json');
}

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

registerLocaleData(localeDe, 'de');
const log = new Logger('AppModule');

// azure logger
export function loggerCallback(logLevel, message, piiEnabled) {
  log.debug('client logging' + message);
}

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PlatformModule,
    AppRoutingModule,
    HttpClientModule,

    SharedModule.forRoot(),
    AngularResizedEventModule,
    MultilingualModule.forRoot([
      {
        provide: TranslateLoader,
        deps: [HttpClient],
        useFactory: HttpLoaderFactory
      }
    ]),
    AppStoreModule,
    MaterialModule,
    DialogModule
  ],
  declarations: [AppComponent, ToolbarComponent],
  providers: [
    { provide: RouterStateSerializer, useClass: CustomRouterStateSerializer },
    { provide: Languages, useValue: Configuration.GET_SUPPORTED_LANGUAGES() },
    { provide: OWL_DATE_TIME_FORMATS, useValue: Configuration.GET_DATETIME_FORMATS() },
    { provide: OWL_DATE_TIME_LOCALE, useValue: Configuration.GET_APP_LOCALE() },
    { provide: LOCALE_ID, useValue: 'de' },
    { provide: 'struct', useValue: window['Struct']() }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
