import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/login/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  isCollapsed: boolean = true;
  loggedIn: boolean;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    const currentUser = this.authService.currentUserValue;
    this.loggedIn = (currentUser != null)
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  navigateToCalendar() {
    this.router.navigate(["/"]);
    this.isCollapsed = true;
  }

  navigateToReservations() {
    this.router.navigate(["/reservations"]);
    this.isCollapsed = true;
  }

  navigateToNews() {
    this.router.navigate(["/news"]);
    this.isCollapsed = true;
  }

  onSelect(target: String) {

  }

}
