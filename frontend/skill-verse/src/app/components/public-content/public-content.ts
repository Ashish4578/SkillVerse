import { Component, OnInit } from '@angular/core';
import { CourseDetails } from '../../model/course-details/course-details';
import { SkillVerseService } from '../../service/skill-verse-service';
import { error } from 'console';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-public-content',
  imports: [CommonModule],
  templateUrl: './public-content.html',
  styleUrls: ['./public-content.css'],
  standalone: true
})
export class PublicContent implements OnInit {

  courses: CourseDetails[] = [];

  constructor(private skillVerseService: SkillVerseService) { }

  ngOnInit(): void {
    this.skillVerseService.guestPage().subscribe({
      next: (data) => {
        this.courses = data;
      },
      error: (err) => {
        console.error('Error fetching courses:', err);
      }
    }
    )
  };


}