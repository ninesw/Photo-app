import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Album } from 'src/app/common/album';
import { AlbumRequest } from 'src/app/common/album-request';
import { AlbumService } from 'src/app/services/album.service';
import { AlbumListComponent } from '../album-list/album-list.component';

@Component({
  selector: 'app-add-album-dialog',
  templateUrl: './add-album-dialog.component.html',
  styleUrls: ['./add-album-dialog.component.css']
})
export class AddAlbumDialogComponent {
  fileToUpload: File | null = null;
  selectedFiles: FileList;

  constructor(
    public dialogRef: MatDialogRef<AlbumListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Album,
    private albumService: AlbumService,
    private snackBar: MatSnackBar) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
  
  addAlbum(name: string, description: string) {
    if (this.selectedFiles != null && name != null && description != null) {
      this.albumService.addAlbum(name, description, this.selectedFiles).subscribe(
        data => {
          if (data.ok) {
            this.snackBar.open("Album " + name + " added successfully", "Ok", { duration: 2000 });
          } else {
            this.snackBar.open("Error adding album", "", { duration: 2000 });
          }
          this.dialogRef.close();
        }
      );
    } else {
      this.snackBar.open("Please fill all fields", "", { duration: 2000 });
    }

  }


  upload(event: any) {
    this.selectedFiles = event.target.files;    
  }

  openSnackBar(name: string) {
    this.snackBar.open("Album added successfully", "Ok");
  }

}
