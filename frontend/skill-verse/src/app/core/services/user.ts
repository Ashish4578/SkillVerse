import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';


export interface UserProfileResponse {
  id: number;
  username: string;
  email: string;
  fullName: string;
  bio?: string;
  role: string;
  createdAt: string;
}

export interface UpdateUserProfileRequest {
  fullName: string;
  email: string;
  bio?: string;
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userUrl = `${environment.apiGateway}/users`;

  constructor(private http: HttpClient) {}

  /**
   * Get user profile by ID
   */
  getUserProfile(userId: number): Observable<UserProfileResponse> {
    return this.http.get<UserProfileResponse>(`${this.userUrl}/${userId}`);
  }

  /**
   * Get currently logged-in user profile
   */
  getCurrentUserProfile(): Observable<UserProfileResponse> {
    return this.http.get<UserProfileResponse>(`${this.userUrl}/me`);
  }

  /**
   * Update user profile
   */
  updateUserProfile(
    userId: number,
    request: UpdateUserProfileRequest
  ): Observable<UserProfileResponse> {
    return this.http.put<UserProfileResponse>(
      `${this.userUrl}/${userId}`,
      request
    );
  }

  /**
   * Delete user account
   */
  deleteUser(userId: number): Observable<string> {
    return this.http.delete(`${this.userUrl}/${userId}`, {
      responseType: 'text'
    });
  }

  /**
   * Search users by username
   */
  searchUsers(username: string): Observable<UserProfileResponse[]> {
    return this.http.get<UserProfileResponse[]>(
      `${this.userUrl}/search?username=${username}`
    );
  }

  /**
   * Get all users (Admin)
   */
  getAllUsers(): Observable<UserProfileResponse[]> {
    return this.http.get<UserProfileResponse[]>(`${this.userUrl}`);
  }
}