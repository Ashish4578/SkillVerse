import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService, UserProfileResponse } from '../../../core/services/user';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.html',
  styleUrls: ['./profile.css']
})
export class Profile implements OnInit {
  userProfile: UserProfileResponse | null = null;
  loading: boolean = true;
  errorMessage: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.loading = true;
    this.errorMessage = '';

    this.userService.getCurrentUserProfile().subscribe({
      next: (response) => {
        this.userProfile = response;
        this.loading = false;

        console.log('User Profile Loaded:', response);
      },
      error: (error) => {
        this.loading = false;
        this.errorMessage =
          error?.error?.message || 'Failed to load profile';

        console.error('Profile Load Failed:', error);
      }
    });
  }
}