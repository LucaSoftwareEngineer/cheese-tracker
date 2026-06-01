import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

interface LoginRequest {
  username: string;
  password: string;
}

/**
 * Servizio che gestisce le chiamate di autenticazione al backend.
 * Si aspetta che il backend imposti il JWT come cookie HttpOnly.
 */
@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private readonly baseUrl = 'http://localhost:8081/api/auth';

  /**
   * Invia richiesta di autenticazione. 
   * Il backend imposterà il cookie HttpOnly.
   * Se l'autenticazione va a buon fine l'utente viene reindirizzato alla dashboard.
   */
  login(request: LoginRequest): void {
    this.http
      .post(this.baseUrl + '/login', request, { withCredentials: true })
      .subscribe({
        next: () => this.router.navigate(['/dashboard']),
        error: (err) => console.error('Login failed', err),
      });
  }
}
