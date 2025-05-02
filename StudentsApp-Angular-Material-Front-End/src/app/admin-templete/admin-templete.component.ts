import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../services/authentification.service";

@Component({
  selector: 'app-admin-templete',
  templateUrl: './admin-templete.component.html',
  styleUrl: './admin-templete.component.css'
})
export class AdminTempleteComponent implements  OnInit{
  constructor (public authService : AuthentificationService) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();

  }
}
