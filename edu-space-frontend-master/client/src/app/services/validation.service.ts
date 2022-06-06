import { FormGroup, ValidatorFn } from '@angular/forms';
import { compareAsc, parseISO } from 'date-fns';

export class ValidationService {
  dateLessThan(from: string, to: string): ValidatorFn {
    return (group: FormGroup): { [key: string]: any } => {
      const fromControl = group.controls[from];
      const toControl = group.controls[to];
      const isCorrectRange = compareAsc(parseISO(fromControl.value), parseISO(toControl.value));
      if (fromControl.value && toControl.value && isCorrectRange !== -1) {
        return {
          dateRangeError: true
        };
      } else {
        return {};
      }
    };
  }
}

