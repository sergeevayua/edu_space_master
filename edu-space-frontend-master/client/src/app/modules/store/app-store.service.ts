import { Action, Store } from '@ngrx/store';

import { State } from './app.reducers';

export abstract class StoreService {
  protected store: Store<State>;

  protected dispatchAction(action: Action) {
    this.store.dispatch(action);
  }

  abstract dispatchLoadAction();
  abstract dispatchCreateAction(record: any);
  abstract dispatchUpdateAction(record: any);
  abstract dispatchDropAction({ id: any });
}


