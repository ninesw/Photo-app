import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumPhotoListComponent } from './album-photo-list.component';

describe('AlbumPhotoListComponent', () => {
  let component: AlbumPhotoListComponent;
  let fixture: ComponentFixture<AlbumPhotoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlbumPhotoListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbumPhotoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
