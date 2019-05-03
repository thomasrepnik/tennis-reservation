import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/login/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  loggedIn: boolean;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    const currentUser = this.authService.currentUserValue;
    this.loggedIn = (currentUser != null)
  }

  navigateToCalendar() {
    this.router.navigate(["/"]);
  }

  navigateToReservations() {
    this.router.navigate(["/reservations"]);
  }

  navigateToNews() {
    this.router.navigate(["/news"]);
  }

  onSelect(target: String) {

  }

}
