import { Component, OnInit } from '@angular/core';
import { SkillVerseService } from '../../service/skill-verse-service';
import { Profile } from '../../model/profile';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile-page',
  imports: [CommonModule],
  templateUrl: './profile-page.html',
  styleUrls: ['./profile-page.css'],
  standalone: true
})
export class ProfilePage implements OnInit {

  profile: Profile | null = null;
  constructor(private service: SkillVerseService) {
  }
  ngOnInit(): void {
      const userId = 18; 
    this.service.getStudentProfile(userId).subscribe({
      next: (data) => {
        console.log('Profile data fetched successfully:', data);
        this.profile = data;
      },
      error: (err) => {
        console.log('Error fetching profile data:', err);
      }
    })
  }

}
