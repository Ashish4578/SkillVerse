import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

/* =========================
   Request Models
========================= */

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

/* =========================
   Auth Response Model
========================= */

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
}

/* =========================
   Auth Service
========================= */

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = `${environment.apiGateway}/auth`;

  constructor(private http: HttpClient) {}

  /**
   * Register new user
   */
  register(request: RegisterRequest): Observable<string> {
    return this.http.post(`${this.authUrl}/register`, request, {
      responseType: 'text'
    });
  }

  /**
   * Login user
   */
  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.authUrl}/login`, request);
  }

  /**
   * Logout user
   */
  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('tokenType');
    localStorage.removeItem('expiresIn');
  }

  /**
   * Save tokens after login
   */
  saveTokens(response: AuthResponse): void {
    localStorage.setItem('accessToken', response.accessToken);
    localStorage.setItem('refreshToken', response.refreshToken);
    localStorage.setItem('tokenType', response.tokenType);
    localStorage.setItem('expiresIn', response.expiresIn.toString());
  }

  /**
   * Get access token
   */
  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  /**
   * Get refresh token
   */
  getRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');
  }

  /**
   * Get token type
   */
  getTokenType(): string | null {
    return localStorage.getItem('tokenType');
  }

  /**
   * Check authentication
   */
  isAuthenticated(): boolean {
    return !!this.getAccessToken();
  }
}