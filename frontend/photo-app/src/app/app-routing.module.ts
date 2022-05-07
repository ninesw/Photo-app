import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlbumListComponent } from './components/album-list/album-list.component';
import { AlbumPhotoListComponent } from './components/album-photo-list/album-photo-list.component';
import { PhotoComponent } from './components/photo/photo.component';

const routes: Routes = [
  { path: 'albums/:albumId', component: AlbumPhotoListComponent },
  { path: 'albums/search/:keyword', component: AlbumListComponent },
  { path: 'photos/:photoId', component: PhotoComponent },
  { path: 'albums', component: AlbumListComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
