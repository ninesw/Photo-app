import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Album } from '../common/album';
import { AlbumRequest } from '../common/album-request';
import { Photo } from '../common/photo';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
    currentId: number;
    url: string = "http://localhost:8080/api/albums";

  constructor(private httpClient: HttpClient) { }

  addAlbum(name: string, description: string, files: FileList): Observable<HttpResponse<AlbumRequest>> {
    const formParams: FormData = new FormData();
    formParams.append("file", files.item(0));
    formParams.append("name", name);
    formParams.append("desc", description);

    return this.httpClient.post<AlbumRequest>(this.url, formParams,
      {observe: 'response'});
  }

  editAlbum(albumDto: any): Observable<HttpResponse<Album>> { { 
    return this.httpClient.put<Album>(this.url, albumDto,
        {headers: {'Content-Type': 'application/json'}, observe: 'response'});
    };
  } 

  deleteAlbum(id: number): Observable<HttpResponse<Album>> { 
    return this.httpClient.delete<Album>(`${this.url}?albumId=${id}`,
        {observe: 'response'});
  };

  getAlbums(): Observable<Album[]> {
    return this.httpClient.get<Album[]>(this.url);
  }

  getAlbumsBySearch(keyword: string): Observable<Album[]> {
    const url: string = `${this.url}/search?keyword=${keyword}`;
    return this.httpClient.get<Album[]>(url);
  }

  getAlbumPhotos(id: number): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>(`${this.url}/${id}/photos`);
  }
}
