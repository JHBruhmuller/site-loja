import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CashOperationsComponent } from './cash-operations.component';

describe('CashOperationsComponent', () => {
  let component: CashOperationsComponent;
  let fixture: ComponentFixture<CashOperationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CashOperationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CashOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
