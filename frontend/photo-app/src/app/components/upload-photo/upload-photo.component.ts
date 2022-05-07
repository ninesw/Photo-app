import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxFileDropEntry } from 'ngx-file-drop';
import { Subject } from 'rxjs';
import { Photo } from 'src/app/common/photo';
import { PhotoService } from 'src/app/services/photo.service';

@Component({
  selector: 'app-upload-photo',
  templateUrl: './upload-photo.component.html',
  styleUrls: ['./upload-photo.component.css'],
})

export class UploadPhotoComponent {
  albumId: number;
  photoUploadSubject: Subject<HttpResponse<Photo>> = new Subject();

  constructor(private photoService: PhotoService,
              private route: ActivatedRoute) {
    this.route.paramMap.subscribe(() => {
      this.albumId = +this.route.snapshot.paramMap.get('albumId');
    }); 
  }

  public files: NgxFileDropEntry[] = [];

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);
          this.photoService.uploadPhoto(file, this.albumId);
        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event){
    console.log(event);
  }

  public fileLeave(event){
    console.log(event);
  }
}
