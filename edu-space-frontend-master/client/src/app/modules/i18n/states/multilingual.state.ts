export interface IMultilingualState {
  lang: string;
}

export const initialState: IMultilingualState = {
  lang: 'en'
};

export const getLang = (state: IMultilingualState) => state.lang;
