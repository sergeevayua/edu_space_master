import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  template: `
    <h2 mat-dialog-title>{{ title }}</h2>
    <div mat-dialog-content>
      <p class="nm">{{ message }}</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="dialogRef.close(true)" tabindex="2">Да</button>
      <button mat-button (click)="dialogRef.close()" tabindex="-1">Отменить</button>
    </div>
  `
})
export class ConfirmationComponent {

  title: string;
  message: string;

  constructor(
    public dialogRef: MatDialogRef<ConfirmationComponent>
  ) { }
}
