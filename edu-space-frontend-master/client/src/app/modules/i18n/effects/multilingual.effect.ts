// angular
import { Injectable, Inject } from '@angular/core';

// libs
import { Store, Action } from '@ngrx/store';
import { Effect, Actions, ofType, createEffect } from '@ngrx/effects';
import { includes, map as _map } from 'lodash';
import { Observable } from 'rxjs';
import { catchError, exhaustMap, map, tap } from 'rxjs/operators';

// module
import { MultilingualService, Languages } from '../services/multilingual.service';
import * as multilingual from '../actions/multilingual.action';

@Injectable()
export class MultilingualEffects {

  change$ = createEffect(() =>
    this.actions$.pipe(
      ofType(multilingual.ActionTypes.CHANGE),
      map((action: any) => {
        if (action.payload) {
          const lang = action.payload;
          if (includes(map(this.languages, 'code'), lang)) {
            return new multilingual.LangChangedAction(lang);
          } else {
            return new multilingual.LangUnsupportedAction(lang);
          }
        } else {
          return new multilingual.LangUnsupportedAction('en');
        }
      }),
    )
  );

  constructor(
    private store: Store<any>,
    private actions$: Actions,
    private multilangService: MultilingualService,
    @Inject(Languages) private languages: any
  ) { }
}
