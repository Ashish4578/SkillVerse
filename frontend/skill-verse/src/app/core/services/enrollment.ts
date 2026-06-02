import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';


export interface EnrollmentRequest {
  userId: number;
  courseId: number;
}

export interface EnrollmentResponse {
  id: number;
  userId: number;
  courseId: number;
  enrollmentDate: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  private enrollmentUrl = `${environment.apiGateway}/enrollments`;

  constructor(private http: HttpClient) {}

  /**
   * Enroll a user in a course
   */
  enrollInCourse(request: EnrollmentRequest): Observable<EnrollmentResponse> {
    return this.http.post<EnrollmentResponse>(`${this.enrollmentUrl}`, request);
  }

  /**
   * Get all enrollments for a user
   */
  getUserEnrollments(userId: number): Observable<EnrollmentResponse[]> {
    return this.http.get<EnrollmentResponse[]>(
      `${this.enrollmentUrl}/user/${userId}`
    );
  }

  /**
   * Get all students enrolled in a course
   */
  getCourseEnrollments(courseId: number): Observable<EnrollmentResponse[]> {
    return this.http.get<EnrollmentResponse[]>(
      `${this.enrollmentUrl}/course/${courseId}`
    );
  }

  /**
   * Check if user is enrolled in a course
   */
  isUserEnrolled(userId: number, courseId: number): Observable<boolean> {
    return this.http.get<boolean>(
      `${this.enrollmentUrl}/check?userId=${userId}&courseId=${courseId}`
    );
  }

  /**
   * Cancel enrollment
   */
  cancelEnrollment(enrollmentId: number): Observable<string> {
    return this.http.delete(`${this.enrollmentUrl}/${enrollmentId}`, {
      responseType: 'text'
    });
  }
}