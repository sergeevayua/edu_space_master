import { Injectable, Pipe, PipeTransform } from '@angular/core';
import { format, fromUnixTime, isValid } from 'date-fns';

@Pipe({ name: 'appDateFormatPipe' })
@Injectable()
export class DateFormatPipe implements PipeTransform {
  transform(timestamp: any, fmt: string): string {

    const getUTCDate = (date) => {
      return new Date(
        date.getUTCFullYear(),
        date.getUTCMonth(),
        date.getUTCDate(),
        date.getUTCHours(),
        date.getUTCMinutes(),
        date.getUTCSeconds(),
      );
    };

    const _timestamp = timestamp / 1000;
    const localDate = fromUnixTime(_timestamp);
    const utcDate = getUTCDate(localDate);
    return isValid(utcDate) ? format(utcDate, fmt, {}) : 'Invalid date';
  }
}

