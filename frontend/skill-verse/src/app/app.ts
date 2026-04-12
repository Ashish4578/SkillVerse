import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { PublicContent } from "./components/public-content/public-content";
import { Register } from "./components/register/register";
import { SignUp } from "./components/sign-up/sign-up";
import { ProfilePage } from './components/profile-page/profile-page';


@Component({
  selector: 'app-root',
  imports: [PublicContent, Register, SignUp, ProfilePage],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('skill-verse');
  view: 'home' | 'signup' | 'register' | 'profile' = 'home';

  showSignup() {
    this.view = 'signup';
  }

  showRegister() {
    this.view = 'register';
  }
  showProfile() {
    this.view = 'profile';
  }
}

