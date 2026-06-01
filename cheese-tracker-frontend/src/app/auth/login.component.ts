import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

/**
 * Componente di login.
 * Il backend imposta un cookie JWT HttpOnly;
 * Dopo l'autenticazione l'utente viene reindirizzato al DashboardComponent;
 */
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';

  private auth = inject(AuthService);
  private router = inject(Router);

  onSubmit(): void {
    this.auth.login({ username: this.username, password: this.password });
  }
}
