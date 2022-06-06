import { CourseGroup } from './courseGroup';
import { Module } from './module';

export interface Course {
  id?: any;
  name?: string;
  courseGroup?: CourseGroup;
  modules?: Array<Module>;
}
