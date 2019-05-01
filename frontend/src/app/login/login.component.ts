import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from './authentication.service';
import { Login } from '../models/login';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  returnUrl: string;
  loginForm: FormGroup;

  constructor(private authService: AuthenticationService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required)
    })

    // reset login status
    this.authService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    const user = this.authService.login(this.loginForm.value['username'], this.loginForm.value['password'])
      .subscribe(user => {
        console.log(user)
        this.router.navigate([this.returnUrl]);
      })


  }

}
