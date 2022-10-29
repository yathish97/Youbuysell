import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { User} from '../user';
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  constructor(private httcli : HttpClient) { }
  fetchTokenfromserver(userdata : any) : Observable<any>
  {
   return  this.httcli.post('http://localhost:9096/authentication/login',userdata);
  }

register(usrobj : User) : Observable<User>
{
  return this.httcli.post<User>("http://localhost:9096/authentication/register",usrobj);
}

storeMytoken(tok:any)
{
  sessionStorage.setItem("mytoken",tok);
}
getMytoken()
{
  return sessionStorage.getItem("mytoken");
}
storemyemailid(emailid:any){
  sessionStorage.setItem("emailid",emailid);
}
getMymailid()
{
  return sessionStorage.getItem("emailid");
}
logout(){
  sessionStorage.clear();
}
}




 