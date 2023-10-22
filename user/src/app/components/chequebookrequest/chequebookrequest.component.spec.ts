import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChequebookrequestComponent } from './chequebookrequest.component';

describe('ChequebookrequestComponent', () => {
  let component: ChequebookrequestComponent;
  let fixture: ComponentFixture<ChequebookrequestComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChequebookrequestComponent]
    });
    fixture = TestBed.createComponent(ChequebookrequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
