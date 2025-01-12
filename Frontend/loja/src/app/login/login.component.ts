import { Component } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginData = { username: '', password: '' };
  isLoading = false;
  errorMessage: string | null = null;

  constructor(private http: HttpClient, private router: Router) { }

  onSubmit() {
    this.isLoading = true;
    this.errorMessage = null;

    this.http
      .post('http://localhost:8080/auth/login', this.loginData)
      .subscribe({
        next: (response: any) => {
          localStorage.setItem('token', response.token);
          this.router.navigate(['/products']); // Altere para a rota desejada
        },
        error: (error) => {
          this.errorMessage = error.error.message || 'Erro ao fazer login.';
          this.isLoading = false;
        },
        complete: () => (this.isLoading = false)
      });
  }
}
