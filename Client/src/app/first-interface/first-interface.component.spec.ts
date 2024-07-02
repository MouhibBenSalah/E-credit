import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstInterfaceComponent } from './first-interface.component';

describe('FirstInterfaceComponent', () => {
  let component: FirstInterfaceComponent;
  let fixture: ComponentFixture<FirstInterfaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FirstInterfaceComponent]
    });
    fixture = TestBed.createComponent(FirstInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
