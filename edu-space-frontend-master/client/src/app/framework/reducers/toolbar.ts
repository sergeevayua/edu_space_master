import * as toolbar from '../actions/toolbar';

export interface State {
  showToolbar: string;
}

const initialState: State = {
  showToolbar: 'out',
};

export function reducer(state = initialState, action: toolbar.Actions): State {
  switch (action.type) {
    case toolbar.CLOSE_TOOLBAR:
      return {
        showToolbar: 'out'
      };

    case toolbar.OPEN_TOOLBAR:
      return {
        showToolbar: 'in'
      };

    default:
      return state;
  }
}

export const getShowToolbar = (state: State) => state.showToolbar;
