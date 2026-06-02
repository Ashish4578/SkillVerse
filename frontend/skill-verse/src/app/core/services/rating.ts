import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';


export interface RatingRequest {
  userId: number;
  courseId: number;
  rating: number;
  review: string;
}

export interface RatingResponse {
  id: number;
  userId: number;
  courseId: number;
  rating: number;
  review: string;
  createdAt: string;
}


@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private ratingUrl = `${environment.apiGateway}/ratings`;

  constructor(private http: HttpClient) {}

  /**
   * Submit rating and review for a course
   */
  submitRating(request: RatingRequest): Observable<RatingResponse> {
    return this.http.post<RatingResponse>(`${this.ratingUrl}`, request);
  }

  /**
   * Get all ratings for a course
   */
  getCourseRatings(courseId: number): Observable<RatingResponse[]> {
    return this.http.get<RatingResponse[]>(
      `${this.ratingUrl}/course/${courseId}`
    );
  }

  /**
   * Get all ratings submitted by a user
   */
  getUserRatings(userId: number): Observable<RatingResponse[]> {
    return this.http.get<RatingResponse[]>(
      `${this.ratingUrl}/user/${userId}`
    );
  }

  /**
   * Update existing rating
   */
  updateRating(ratingId: number, request: RatingRequest): Observable<RatingResponse> {
    return this.http.put<RatingResponse>(
      `${this.ratingUrl}/${ratingId}`,
      request
    );
  }

  /**
   * Delete rating
   */
  deleteRating(ratingId: number): Observable<string> {
    return this.http.delete(`${this.ratingUrl}/${ratingId}`, {
      responseType: 'text'
    });
  }

  /**
   * Get average course rating
   */
  getAverageCourseRating(courseId: number): Observable<number> {
    return this.http.get<number>(
      `${this.ratingUrl}/course/${courseId}/average`
    );
  }
}