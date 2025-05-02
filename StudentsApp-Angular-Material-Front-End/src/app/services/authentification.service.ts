import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
  public username : any;
  public roles : any ;
  public authenticate : boolean = false;
  public users:any = {
    'admin': ['STUDENT','ADMIN'],
    'user1':['STUDENT']
  }
  //dans le services on cree une methode qui va etre un service
  constructor(private router  : Router) { }

  public login(username : string , password : string){
    if(this.users[username] && password =="1234"){
      this.username = username ;
      this.roles = this.users[username];
      this.authenticate = true ;
      return true;
    } else {
      return false ;
    }
  }

  logout() {
    this.authenticate = false;
    this.username = undefined;
    this.roles = undefined;
    this.router.navigateByUrl("/login")
  }
}
