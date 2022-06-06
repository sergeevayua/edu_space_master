import { Course } from './course';
import { Chapter } from './chapter';

export interface Module {
  id?: any;
  name?: string;
  course?: Course;
  chapters?: Array<Chapter>;
}
