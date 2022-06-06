import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { ConfirmationComponent } from './confirmation/confirmationDialog.component';

const defaultDialogConfig = new MatDialogConfig();

@Injectable()
export class DialogService {

  constructor(private dialog: MatDialog) {
  }

  confirm(title: string, message: string): Observable<boolean> {
    let dialogRef: MatDialogRef<ConfirmationComponent>;
    dialogRef = this.dialog.open(ConfirmationComponent);
    dialogRef.componentInstance.title = title;
    dialogRef.componentInstance.message = message;

    return dialogRef.afterClosed();
  }

}
