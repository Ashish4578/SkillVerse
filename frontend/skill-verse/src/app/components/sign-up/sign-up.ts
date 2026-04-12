import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  imports: [FormsModule],
  templateUrl: './sign-up.html',
  styleUrls: ['./sign-up.css'],
  standalone: true
})
export class SignUp {

  signUpData = {
    username: '',
    role: 'STUDENT',
    password: ''
  };
  onSubmit() {
    // Handle form submission logic here
    console.log('Form submitted!' + JSON.stringify(this.signUpData));
  }
}
