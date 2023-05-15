import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebService2ViewComponent } from './web-service2-view.component';

describe('WebService2ViewComponent', () => {
  let component: WebService2ViewComponent;
  let fixture: ComponentFixture<WebService2ViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WebService2ViewComponent]
    });
    fixture = TestBed.createComponent(WebService2ViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
