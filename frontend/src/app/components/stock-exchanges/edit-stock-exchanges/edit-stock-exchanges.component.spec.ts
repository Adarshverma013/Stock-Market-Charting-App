import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStockExchangesComponent } from './edit-stock-exchanges.component';

describe('EditStockExchangesComponent', () => {
  let component: EditStockExchangesComponent;
  let fixture: ComponentFixture<EditStockExchangesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditStockExchangesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditStockExchangesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
