import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Album } from 'src/app/common/album';
import { AlbumService } from 'src/app/services/album.service';
import { AddAlbumDialogComponent } from '../add-album-dialog/add-album-dialog.component';
import { EditAlbumDialogComponent } from '../edit-album-dialog/edit-album-dialog.component';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.css']
})
export class AlbumListComponent implements OnInit {

  searchMode: boolean = false;
  albums: Album[] = [];
  
  constructor(private albumService: AlbumService,
              private dialog: MatDialog,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.loadAlbums();
    });
  }

  loadAlbums() {
    this.searchMode = this.route.snapshot.paramMap.has("keyword")

    if (this.searchMode) {
      console.log("SEARCH MODE");
      const keyword: string = this.route.snapshot.paramMap.get("keyword");
      this.albumService.getAlbumsBySearch(keyword).subscribe(
        data => {
          this.albums = data;
        }
      )  
    } else {
      this.albumService.getAlbums().subscribe(
        data => {
          this.albums = data;
        }
      )  
    }
  }

  handleEdit(albumId: number) {
    const dialogRef = this.dialog.open(EditAlbumDialogComponent, {
      data: this.albums.find(album => album.id === albumId),
      width: '600px',
      height: '320px',
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }

  handleAddAlbum() {
    const dialogRef = this.dialog.open(AddAlbumDialogComponent, {
      width: '600px',
      height: '320px',
      data: new Album(),
    })

    // call load albums after closing the dialog
  }

  handleDelete(albumId: number) {
    // delete album with id albumId using albumService then reload the albums
    this.albumService.deleteAlbum(albumId).subscribe(
      data => {
        if (data.ok) {
          this.snackBar.open("Album deleted", "Ok", { duration: 2000 });
        }
        this.loadAlbums();
      }
    )
  
  }

}
