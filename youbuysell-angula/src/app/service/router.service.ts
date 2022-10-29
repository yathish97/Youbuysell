import { Injectable } from '@angular/core';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router:Router) { }
  openlogin(){
    this.router.navigate(['login']);

  }
  openhome(){
    this.router.navigate(['home']);
  }
  opencart(){
    this.router.navigate(['cart']);
  }
  openproduct(){
    
    this.router.navigate(['products']);
  }
 
}