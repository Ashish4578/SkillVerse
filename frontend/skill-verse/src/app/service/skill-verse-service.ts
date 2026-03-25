import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseDetails } from '../model/course-details/course-details';

@Injectable({
  providedIn: 'root',
})
export class SkillVerseService {

  private baseUrl = 'http://localhost:8088/skillverse/users'; 
  
  private registerUrl = 'http://localhost:8088/skillverse/users/register';

  constructor(private http: HttpClient) {
  }
  
  guestPage(): Observable<any> {
    return this.http.get<CourseDetails[]>(this.baseUrl);
  }
registerUser(userData: any): Observable<any> {
    return this.http.post(this.registerUrl, userData);
  }
}