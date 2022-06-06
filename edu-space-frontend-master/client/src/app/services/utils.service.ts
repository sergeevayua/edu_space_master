import { Injectable } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';
import * as _ from 'lodash';
import isNil from 'lodash/isNil';
import isPlainObject from 'lodash/isPlainObject';
import { HttpParams } from '@angular/common/http';
import { Logger } from '@app/services/logger.service';
import { JwtHelperService } from '@auth0/angular-jwt';

const log = new Logger('UtilsService');
const helper = new JwtHelperService();

@Injectable({ providedIn: 'root' })
export class UtilsService {

  constructor() {
  }

  /** HTTP params helper */
  buildQueryParams(source: Object): HttpParams {
    let target: HttpParams = new HttpParams();
    Object.keys(source).forEach((key: string) => {
      let value: any = source[key];
      if (isNil(value)) {
        return;
      }
      if (isPlainObject(value)) {
        value = JSON.stringify(value);
      } else {
        value = value.toString();
      }
      target = target.append(key, value);
    });
    return target;
  }

  /** Form group utils */
  // apply for values
  applyFormValues(group: FormGroup, formValues: any): any {
    if (formValues) {
      return Object.keys(formValues).forEach(key => {
        const formControl = group.controls[key];
        if (group.controls[key]) {
          // log.debug('applyFormValues')
          // log.debug(key)
          switch (key) {
            case 'actions':
              break;
            default:
              formControl.setValue(formValues[key]);
          }
        }
      });
    }
  }

  // [async] Set query params
  setQueryValueByFormGroupName(query: any, ...groups: Array<FormGroup>): Promise<any> {
    const _query = Object.assign({}, query);

    return new Promise((resolve) => {
      for (const group of groups) {
        Object.keys(group.controls).forEach(key => {
          const value = group.get(key).value;
          if (value) {
            switch (key) {
              default:
                _query[key] = `${value}`;
            }
          } else {
            delete _query[key];
          }
        });
        resolve(_query);
      }
    });
  }

  // [async] Reset form group controls
  resetReportParams(form: FormGroup): Promise<any> {

    return new Promise((resolve) => {
      let control: AbstractControl = null;
      form.markAsUntouched();
      Object.keys(form.controls).forEach((controlGroupName) => {
        if (controlGroupName !== 'dateTime') {
          const formGroup: any = form.controls[controlGroupName];
          formGroup.reset();
          Object.keys(formGroup['controls']).forEach((controlGroupItemName) => {
            control = formGroup['controls'][controlGroupItemName];
            control.setValue(undefined);
            control.setErrors(null);
          });
        }
      });
      resolve(form);
    });
  }

  /** Service utils */
  findObjectIndex(list: any[], obj: any, key: string): number {

    return _.findIndex(list, function (item) {
      return obj[key] === item[key];
    });
  }

  findObjectByQuery(list: any[], key: string, query: string): any {

    return _.find(list, function (item) {
      return item[key].toLowerCase() === query.toLowerCase();
    });
  }

  waitFor(ms: number): any {
    return new Promise(r => setTimeout(r, ms));
  }

  async asyncForEach(array: any, callback: any) {
    for (let index = 0; index < array.length; index++) {
      await callback(array[index], index, array)
    }
  }

  /** Get status color class */

  getStatusClass(element: any): string {
    switch (element.stateId) {
      case 0:
        return `status-ok`;
      case 1:
        return `status-nm`;
      case 2:
        return `status-sdce`;
      case 3:
        return `status-alarm`;
      default:
        return `status-ok`;
    }
  }

  public checkPermissions(permissions: Array<any>, permission: string): boolean {
    return permissions.includes(permission);
  }

  public getUserPermissions(): any[] {
    const payload = helper.decodeToken(localStorage.getItem('accessToken'));
    log.debug('$$$$$ getUserInfo permissions', payload?.permissions);
    return payload?.permissions;
  }

  public getUrlParams(search) {
    const hashes = search.slice(search.indexOf('?') + 1).split('&')
    const params = {}
    hashes.map(hash => {
      const [key, val] = hash.split('=')
      params[key] = decodeURIComponent(val)
    })
    return params
  }

}
