import { Routes } from '@angular/router';
import { PublicContent } from './components/public-content/public-content';
import { SignUp } from './components/sign-up/sign-up';
import { Register } from './components/register/register';

export const routes: Routes = [

  { path: '', component: PublicContent },
  { path: 'signup', component: SignUp },
  { path: 'register', component: Register }
];
