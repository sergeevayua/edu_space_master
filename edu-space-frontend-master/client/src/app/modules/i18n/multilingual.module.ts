// angular
import {
  CUSTOM_ELEMENTS_SCHEMA,
  ModuleWithProviders,
  NgModule,
  NO_ERRORS_SCHEMA,
  Optional,
  SkipSelf
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
// libs
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { MultiTranslateHttpLoader } from 'ngx-translate-multi-http-loader';

// module
import { MULTILANG_COMPONENTS } from './components';
import { MULTILANG_PROVIDERS } from './services';
// material components
import { MaterialModule } from '@app/framework/material/material.module';

// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
  return new MultiTranslateHttpLoader(httpClient, [
    {
      prefix: './assets/translate/core/', suffix: '.json'
    },
    {
      prefix: './assets/translate/shared/', suffix: '.json'
    },
  ]);
}

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (HttpLoaderFactory),
        deps: [HttpClient]
      }
    }),
    MaterialModule,
  ],
  declarations: [
    ...MULTILANG_COMPONENTS
  ],
  providers: [
    ...MULTILANG_PROVIDERS,
  ],
  exports: [
    ...MULTILANG_COMPONENTS,
    TranslateModule
  ],
  schemas: [
    NO_ERRORS_SCHEMA,
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class MultilingualModule {

  static forRoot(configuredProviders: Array<any>): ModuleWithProviders<MultilingualModule> {
    return {
      ngModule: MultilingualModule,
      providers: configuredProviders
    };
  }

  constructor(@Optional() @SkipSelf() parentModule: MultilingualModule) {
    if (parentModule) {
      throw new Error('MultilingualModule already loaded; Import in root module only.');
    }
  }
}
