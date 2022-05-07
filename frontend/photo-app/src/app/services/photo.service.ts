import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Photo } from '../common/photo';
import { Comment } from '../common/comment';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  uploadPhotoSubject: Subject<Photo> = new Subject();
  
  url: string = "http://localhost:8080/api/photos";

  constructor(private httpClient: HttpClient) {  }

  getPhoto(id: number): Observable<Photo> {
    return this.httpClient.get<Photo>(`${this.url}/${id}`);
  }

  uploadPhoto(file: File, id: number) {
    const formData: FormData = new FormData();
    formData.append("file", file);
    formData.append("albumId", id.toString());
    this.httpClient.post<Photo>(this.url, formData, {observe: 'response'}).subscribe(
      data => {
        if (data.ok) {
          this.uploadPhotoSubject.next(data.body);
        }
      }
    );
  }

  deletePhoto(photoId: number): Observable<HttpResponse<Object>> {
    let url = `${this.url}?photoId=${photoId}`;
    return this.httpClient.delete(url, {observe: 'response'});
  }

  getPhotoComments(photoId: number): Observable<Comment[]> {
    let url = `${this.url}/${photoId}/comments`;
    return this.httpClient.get<Comment[]>(url);
  }
  
}
