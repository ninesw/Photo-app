import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Photo } from 'src/app/common/photo';
import { PhotoService } from 'src/app/services/photo.service';
import { Comment } from 'src/app/common/comment';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  currentPhoto: Photo;
  comments: Comment[];
  value: Comment

  constructor(
    private route: ActivatedRoute,
    private photoService: PhotoService,
    private commentService: CommentService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getPhoto();
      this.getComments();
    });
  }

  getPhoto() {
    const id = +this.route.snapshot.paramMap.get('photoId');
    this.photoService.getPhoto(id).subscribe(photo => {
      console.log(photo);
      this.currentPhoto = photo;
    });
  }
  
  getComments() {
    const id = +this.route.snapshot.paramMap.get('photoId');
    this.photoService.getPhotoComments(id).subscribe(comments => {
      console.log(comments);
      this.comments = comments;
    });
  }

  handleDeleteComment(commentId: number) {
    this.commentService.deleteComment(commentId).subscribe(() => {
      this.getComments();
    });
  }

  handleAddComment(comment: Comment) {
    this.commentService.addComment(comment).subscribe(() => {
      this.getComments();
    });
  }
}

