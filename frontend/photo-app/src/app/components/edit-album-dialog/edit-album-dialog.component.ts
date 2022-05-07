import { HttpResponse, HttpStatusCode } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Album } from 'src/app/common/album';
import { AlbumService } from 'src/app/services/album.service';
import { AlbumListComponent } from '../album-list/album-list.component';

@Component({
  selector: 'app-edit-album-dialog',
  templateUrl: './edit-album-dialog.component.html',
  styleUrls: ['./edit-album-dialog.component.css']
})
export class EditAlbumDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AlbumListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Album,
    private albumService: AlbumService,
    private snackBar: MatSnackBar) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
  
  editAlbum(albumDto: any) {

    if (albumDto.name.length === 0) {
      this.snackBar.open("Album name is required", "Ok", { duration: 3000 });
      return;
    }
    this.albumService.editAlbum(albumDto).subscribe(data => {
      if (data.ok) {
        this.openSnackBar(albumDto.name);
      };    
    });
  }

  openSnackBar(name: string) {
    this.snackBar.open("Album " + name + " updated successfully", "Ok");
  }

}
