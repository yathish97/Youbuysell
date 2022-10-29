import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { RouterService } from 'src/app/service/router.service';
import { User } from 'src/app/user';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  emailid= new FormControl;
  username=new FormControl;
  phonenumber= new FormControl;
  password= new FormControl;
  usrobj : User = new  User();
  userdetail:Array<User>=[];
   constructor(private rserviceobj : RouterService,private authservice:AuthServiceService) {

    this.emailid=new FormControl('',Validators.required);
    this.username=new FormControl('',Validators.required);
    this.phonenumber=new FormControl('',Validators.required);
    this.password=new FormControl('',[Validators.required,Validators.minLength(6)]);
   }
  ngOnInit(): void {
  }

  validateUname() : string
  {
    if (this.emailid.touched && this.emailid.invalid)
    {
     return "emailid can not be null";
    }
    else
    return "";
  }

validatePass() : string
{
  if (this.password.touched && this.password.invalid)
  {
    if (this.password.errors?.['required'])
    return "password cant be null"
     else
    
   return "password should be minimum 6 ";
  }
  else
  return "";

}
  login(){
    {
      let data = {
              "emailid": this.emailid.value,
              "password":this.password.value
                }
                
               
           
          this.authservice.fetchTokenfromserver(data).subscribe (
          (res:any)=>
          {
        
          this.authservice.storeMytoken(res["token"]);  
          sessionStorage.setItem("emailid",this.emailid.value);         
          this.authservice.storemyemailid(res["emailid"]); 
          sessionStorage.setItem("emailid",this.emailid.value);           
            this.rserviceobj.openproduct();
            alert("successfuly login")
           
          },
          (err:any)=>alert("Invalid Username or Password")
          )
       }
      
  }
 
  register(){
      this.authservice.register(this.usrobj).subscribe(
        (res:any)=>{
          alert("REgistered successful") ;
        },(error:any)=>
        {
          alert("User Email already exist try with different Emailid")
        })
        
        
    }
   }