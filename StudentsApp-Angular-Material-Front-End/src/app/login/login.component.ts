import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthentificationService} from "../services/authentification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  public loginFormGroup ! : FormGroup;
  constructor( private fb : FormBuilder ,
               private authService : AuthentificationService,
               private router :Router) {

  }
  ngOnInit(){
    this.loginFormGroup = this.fb.group({
      username : this.fb.control(''),
      password : this.fb.control('')
    });

  }

  Login() {
    let username = this.loginFormGroup.value.username ;
    let password = this.loginFormGroup.value.password;
    let auth: boolean = this.authService.login(username,password);
    if(auth == true){
      this.router.navigateByUrl("/admin")

    }
  }
}
