import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { AlbumListComponent } from './components/album-list/album-list.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { AlbumPhotoListComponent } from './components/album-photo-list/album-photo-list.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatDialogModule} from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import {MatFormFieldModule} from '@angular/material/form-field';
import { EditAlbumDialogComponent } from './components/edit-album-dialog/edit-album-dialog.component';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { SearchComponent } from './components/search/search.component';
import { AddAlbumDialogComponent } from './components/add-album-dialog/add-album-dialog.component';
import { UploadPhotoComponent } from './components/upload-photo/upload-photo.component';
import { NgxFileDropModule } from 'ngx-file-drop';
import { PhotoComponent } from './components/photo/photo.component';
import {MatListModule} from '@angular/material/list';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    AlbumListComponent,
    AlbumPhotoListComponent,
    EditAlbumDialogComponent,
    SearchComponent,
    AddAlbumDialogComponent,
    UploadPhotoComponent,
    PhotoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatGridListModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatMenuModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatSnackBarModule,
    NgxFileDropModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
