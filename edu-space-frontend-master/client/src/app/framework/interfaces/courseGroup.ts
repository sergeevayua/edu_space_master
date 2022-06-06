import { Course } from './course';

export interface CourseGroup {
  id?: any;
  name?: string;
  course: Array<Course>;
}
