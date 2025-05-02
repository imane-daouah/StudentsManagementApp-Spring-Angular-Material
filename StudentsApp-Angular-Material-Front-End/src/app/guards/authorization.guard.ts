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
export class AuthorizationGuard implements CanActivate{
  constructor(private authService: AuthentificationService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    let authorize: boolean = false;
    let authorizedRoles: string[] = route.data['roles'];
    let roles: string[] = this.authService.roles;
    console.log(roles);
    for (let i: number = 0; i < roles.length; i++) {
      if (authorizedRoles.includes(roles[i])) {
        authorize = true;
      }
    }
    return authorize;

  }
}


