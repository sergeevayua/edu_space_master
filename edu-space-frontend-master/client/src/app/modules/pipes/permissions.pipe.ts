import { Injectable, Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'appPermissionsPipe', pure: false })
@Injectable()
export class PermissionsPipe implements PipeTransform {
  transform(items: any[], filter: any[]): any {
    if (!items || !filter) {
      return [];
    }
    const _items = items.filter(i => filter.includes(i.permission));
    return _items;
  }
}

