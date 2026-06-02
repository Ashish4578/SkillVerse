import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface CourseRequest {
  title: string;
  description: string;
  instructorName: string;
  category: string;
  price: number;
}

export interface CourseResponse {
  id: number;
  title: string;
  description: string;
  instructorName: string;
  category: string;
  price: number;
  averageRating: number;
  totalRatings: number;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private courseUrl = `${environment.apiGateway}/courses`;

  constructor(private http: HttpClient) {}

  /**
   * Create a new course
   */
  createCourse(request: CourseRequest): Observable<CourseResponse> {
    return this.http.post<CourseResponse>(`${this.courseUrl}`, request);
  }

  /**
   * Get all courses
   */
  getAllCourses(): Observable<CourseResponse[]> {
    return this.http.get<CourseResponse[]>(`${this.courseUrl}`);
  }

  /**
   * Get course by ID
   */
  getCourseById(courseId: number): Observable<CourseResponse> {
    return this.http.get<CourseResponse>(`${this.courseUrl}/${courseId}`);
  }

  /**
   * Update course
   */
  updateCourse(courseId: number, request: CourseRequest): Observable<CourseResponse> {
    return this.http.put<CourseResponse>(`${this.courseUrl}/${courseId}`, request);
  }

  /**
   * Delete course
   */
  deleteCourse(courseId: number): Observable<string> {
    return this.http.delete(`${this.courseUrl}/${courseId}`, {
      responseType: 'text'
    });
  }

  /**
   * Search courses by keyword
   */
  searchCourses(keyword: string): Observable<CourseResponse[]> {
    return this.http.get<CourseResponse[]>(
      `${this.courseUrl}/search?keyword=${keyword}`
    );
  }

  /**
   * Filter courses by category
   */
  getCoursesByCategory(category: string): Observable<CourseResponse[]> {
    return this.http.get<CourseResponse[]>(
      `${this.courseUrl}/category/${category}`
    );
  }
}