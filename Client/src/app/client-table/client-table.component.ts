import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from '../entities/user';

@Component({
  selector: 'app-client-table',
  templateUrl: './client-table.component.html',
  styleUrls: ['./client-table.component.css']
})
export class ClientTableComponent {
  authors: User[] = []; // Rename to authors if you want to match the template

  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.authService.getAllClients().subscribe(
      (data: User[]) => {
        this.authors = data; // Assign data to authors
      },
      error => {
        console.error('Error fetching users', error);
      }
    );
  }

}
