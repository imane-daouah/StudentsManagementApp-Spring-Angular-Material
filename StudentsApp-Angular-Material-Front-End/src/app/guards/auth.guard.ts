import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthentificationService} from "../services/authentification.service";
//on peux utiliser comme fonction ou comme service (on utilise @injectable )

@Injectable()
export class AuthGuard {
  constructor(private authService : AuthentificationService,
              private router : Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authService.authenticate == true){
      return true;
    } else {
      this.router.navigateByUrl("/login")
      return false;
    }
  }

}


