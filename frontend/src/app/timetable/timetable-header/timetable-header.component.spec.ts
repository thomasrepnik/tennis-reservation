import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TimetableHeaderComponent } from './timetable-header.component';

describe('TimetableHeaderComponent', () => {
  let component: TimetableHeaderComponent;
  let fixture: ComponentFixture<TimetableHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TimetableHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimetableHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
