import { Chapter } from './chapter';

export interface Exercise {
  id?: any;
  numeration?: number;
  description?: string;
  content?: string;
  chapter?: Chapter;
}
