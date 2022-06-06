import { Action } from '@ngrx/store';

export const OPEN_TOOLBAR = '[Toolbar] Open toolbar';
export const CLOSE_TOOLBAR = '[Toolbar] Close toolbar';

export class OpenToolbar implements Action {
  readonly type = OPEN_TOOLBAR;
}

export class CloseToolbar implements Action {
  readonly type = CLOSE_TOOLBAR;
}

export type Actions = OpenToolbar | CloseToolbar;
