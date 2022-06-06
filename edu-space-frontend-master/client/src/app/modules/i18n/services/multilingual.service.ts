// angular
import { Injectable, InjectionToken } from '@angular/core';

// libs
import { Store } from '@ngrx/store';
import { TranslateService } from '@ngx-translate/core';

// app
import { ILang } from '../interfaces/ilang';
import { State } from '../../store/app.reducers';

// module
import { IMultilingualState, initialState } from '../states';
import { ChangeAction } from '../actions';

// provide supported languages at runtime
export const Languages: InjectionToken<Array<ILang>> = new InjectionToken('Languages');
export const LanguageProviders = [{ provide: Languages, useValue: undefined }];

// service
@Injectable({ providedIn: 'root' })
export class MultilingualService {
  category = 'Multilingual';

  constructor(
    private translate: TranslateService,
    private store: Store<State>
  ) {
    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang(initialState.lang);
    const userLang = 'de';
    store.select(s => s.i18n).subscribe((state: IMultilingualState) => {
      this.translate.use(state.lang);
    });

    this.store.dispatch(new ChangeAction(userLang));
  }
}
