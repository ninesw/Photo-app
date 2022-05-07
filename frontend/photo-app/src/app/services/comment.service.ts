import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../common/comment';


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  url = 'http://localhost:8080/api/comments';

  constructor(private httpClient: HttpClient) {}

  getAllComments(): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(this.url);
  }

  addComment(comment: Comment): Observable<Comment> {
    return this.httpClient.post<Comment>(this.url, comment);
  }

  deleteComment(id: number): Observable<Comment> {
    return this.httpClient.delete<Comment>(`${this.url}/${id}`);
  }

}
