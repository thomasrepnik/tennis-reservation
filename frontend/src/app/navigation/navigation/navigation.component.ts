import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/login/authentication.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  loggedIn: boolean;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    const currentUser = this.authService.currentUserValue;
    this.loggedIn = (currentUser != null)
  }

  onSelect(target: String) {

  }

}
