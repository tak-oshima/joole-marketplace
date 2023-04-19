import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupRequest } from 'src/app/core/models/signup-request';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent {
  signupForm: FormGroup;
  signupError: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      userType: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.signupForm.valid) {
      const signupRequest: SignupRequest = this.signupForm.value;
      this.userService.signup(signupRequest).subscribe({
        next: (response) => {
          const token = response.token;
          this.authenticationService.setToken(token);
          this.router.navigate(['/search']);
        },
        error: (error) => {
          console.log(error);
          this.signupError = true;
        },
      });
    }
  }
}
