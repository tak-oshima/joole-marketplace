import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  constructor(private router: Router, private userService: UserService) {}

  isCurrentRoute(route: string): boolean {
    return this.router.url.startsWith(route);
  }

  isLoggedIn(): boolean {
    return this.userService.isLoggedIn();
  }

  onLogoutClick(): void {
    this.userService.logout();
  }

  onLogoClick(): void {
    this.router.navigate(['/search']);
  }

  get username(): string {
    return this.userService.username!;
  }
}
