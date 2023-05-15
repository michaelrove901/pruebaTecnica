import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebService1ViewComponent } from './web-service1-view.component';

describe('WebService1ViewComponent', () => {
  let component: WebService1ViewComponent;
  let fixture: ComponentFixture<WebService1ViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WebService1ViewComponent]
    });
    fixture = TestBed.createComponent(WebService1ViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
