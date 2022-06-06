import { Module } from './module';
import { Exercise } from './exercise';

export interface Chapter {
  id?: any;
  name?: string;
  module?: Module;
  exercises: Array<Exercise>;
}
