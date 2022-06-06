import { Subject } from "rxjs";

export const currentMode = new Subject<string>();

export const currentMode$ = currentMode.asObservable();
