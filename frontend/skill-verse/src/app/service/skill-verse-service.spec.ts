import { TestBed } from '@angular/core/testing';

import { SkillVerseService } from './skill-verse-service';

describe('SkillVerseService', () => {
  let service: SkillVerseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SkillVerseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
