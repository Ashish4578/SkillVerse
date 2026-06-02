import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import {
  AuthService,
  LoginRequest,
  AuthResponse
} from '../../../core/services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  loginForm: FormGroup;
  errorMessage: string = '';
  loading: boolean = false;

  // Added for success response display
  loginSuccess: boolean = false;
  authResponse: AuthResponse | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.loginSuccess = false;

    const loginRequest: LoginRequest = this.loginForm.value;

    this.authService.login(loginRequest).subscribe({
      next: (response: AuthResponse) => {
        this.authService.saveTokens(response);

        this.loading = false;
        this.loginSuccess = true;
        this.authResponse = response;

        this.router.navigate(['/profile']);
      },
      error: (error) => {
        this.loading = false;
        this.loginSuccess = false;

        this.errorMessage =
          error?.error?.message || 'Invalid username or password';

        console.error('Login Failed:', error);
      }
    });
  }
}