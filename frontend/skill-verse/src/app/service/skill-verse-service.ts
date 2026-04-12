import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseDetails } from '../model/course-details';
import { Profile } from '../model/profile';

@Injectable({
  providedIn: 'root',
})
export class SkillVerseService {

  private baseUrl = 'http://localhost:8088/skillverse/users';

  private registerUrl = 'http://localhost:8088/skillverse/users/register';

  private profileUrl = 'http://localhost:8081/skillverse/student/profile';

  constructor(private http: HttpClient) {
  }

  guestPage(): Observable<any> {
    return this.http.get<CourseDetails[]>(this.baseUrl);
  }
  registerUser(userData: any): Observable<any> {
    return this.http.post(this.registerUrl, userData);
  }
  getStudentProfile(userId: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.profileUrl}/${userId}`);
  }
  deleteStudentProfile(userId: number): Observable<Profile> {
    return this.http.delete<Profile>(`${this.profileUrl}/${userId}`);
  }
  updateStudentProfile(userId: number, profileData: Profile): Observable<Profile> {
    return this.http.put<Profile>(`${this.profileUrl}/${userId}`, profileData);
  }
}