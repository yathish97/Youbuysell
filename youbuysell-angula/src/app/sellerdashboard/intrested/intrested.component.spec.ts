import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntrestedComponent } from './intrested.component';

describe('IntrestedComponent', () => {
  let component: IntrestedComponent;
  let fixture: ComponentFixture<IntrestedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntrestedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IntrestedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
