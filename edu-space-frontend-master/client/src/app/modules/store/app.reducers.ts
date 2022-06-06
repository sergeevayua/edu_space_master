import {
  ActionReducerMap,
  createSelector,
  createFeatureSelector,
  ActionReducer,
  MetaReducer,
} from '@ngrx/store';
import { environment } from '@conf/env.config';
import { RouterStateUrl } from '@app/framework/utils/custom-router-state-serializer';
import * as fromRouter from '@ngrx/router-store';

import * as fromMultilingual from '../i18n';
import * as fromLayout from '@app/framework/reducers/layout';
import * as fromToolbar from '@app/framework/reducers/toolbar';
import {Logger} from "@app/services/logger.service";
const log = new Logger('AppReducers');

export interface State {
  layout: fromLayout.State;
  toolbar: fromToolbar.State;
  i18n: fromMultilingual.IMultilingualState;
  routerReducer: fromRouter.RouterReducerState<RouterStateUrl>;
}

export const reducers: ActionReducerMap<any> = {
  layout: fromLayout.reducer,
  toolbar: fromToolbar.reducer,
  i18n: fromMultilingual.reducer,
  routerReducer: fromRouter.routerReducer
};

// console.log all actions
export function logger(reducer: ActionReducer<State>): ActionReducer<State> {
  return function(state: State, action: any): State {
    log.debug('state', state);
    log.debug('action', action);
    return reducer(state, action);
  };
}

export const metaReducers: MetaReducer<State>[] = !environment.production
  ? [logger]
  : [];

/**
 * Layout Reducers
 */
export const getLayoutState: any  = createFeatureSelector<fromLayout.State>('layout');

export const getShowSidenav: any = createSelector(
  getLayoutState,
  fromLayout.getShowSidenav,
);

export const getToolbarState: any = createFeatureSelector<fromToolbar.State>('toolbar');

export const getShowToolbar: any  = createSelector(
  getToolbarState,
  fromToolbar.getShowToolbar
);


/**
 * Router Reducers
 */
export const getRouter: any = createFeatureSelector<fromRouter.RouterReducerState>('routerReducer');

export const getRouterState: any  = createSelector(
  getRouter,
  (routerState: fromRouter.RouterReducerState) => routerState.state
);

/**
 * I18n Reducers
 */
export const getLangState: any = createFeatureSelector<fromMultilingual.IMultilingualState>('i18n');

export const getSelectedLanguage: any  = createSelector(
  getLangState,
  fromMultilingual.getLang
);
