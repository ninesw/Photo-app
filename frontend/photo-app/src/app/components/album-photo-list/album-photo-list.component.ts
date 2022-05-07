import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Photo } from 'src/app/common/photo';
import { AlbumService } from 'src/app/services/album.service';
import { PhotoService } from 'src/app/services/photo.service';

@Component({
  selector: 'app-album-photo-list',
  templateUrl: './album-photo-list.component.html',
  styleUrls: ['./album-photo-list.component.css']
})
export class AlbumPhotoListComponent implements OnInit {

  albumPhotos: Photo[] = [];

  constructor(private route: ActivatedRoute,
              private photoService: PhotoService,
              private albumService: AlbumService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listPhotos();
    })

  }

  listPhotos() {
    const id = +this.route.snapshot.paramMap.get('albumId');
    this.albumService.getAlbumPhotos(id).subscribe(albumPhotos => {
      this.albumPhotos = albumPhotos;
    });

    this.photoService.uploadPhotoSubject.subscribe(photo => {
      this.albumPhotos.push(photo);
    }
    );
  }

  handleDelete(photoId: number) {
    console.log("delete photo: " + photoId);
    
    this.photoService.deletePhoto(photoId).subscribe((resp) => {
      console.log(resp);
      
      this.listPhotos();
    });
  }


}
