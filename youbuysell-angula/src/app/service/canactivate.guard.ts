import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from './authentication.service';
import { RouterService } from './router.service';

@Injectable({
  providedIn: 'root'
})
export class CanactivateGuard implements CanActivate {
  constructor(private authservice:AuthServiceService, private routegaurd: RouterService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let tok=this.authservice.getMytoken();

      if(tok==null)
      {
        this.routegaurd.openlogin();
        return false;
      }
       return true;
   }
   
  }
  
