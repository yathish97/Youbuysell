import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-intrested',
  templateUrl: './intrested.component.html',
  styleUrls: ['./intrested.component.css']
})
export class IntrestedComponent implements OnInit {
  public product : any=[];
  public userprofile : any=[];
  sellerid: any;
  constructor(private productservice:ServiceService,private authservice:AuthServiceService) { }

  ngOnInit(): void {
   this.sellerid= this.authservice.getMymailid();
    this.productservice.getbysellerid(this.sellerid).subscribe(
      (res)=>{
        this.product=res;   
       }
      )      
  }
  viewbuyerdetails(buyeremail:any){

    console.log("email"+buyeremail.buyerid)
    this.productservice.viewprofile(buyeremail.buyerid).subscribe(
      (res)=>{
        this.userprofile=res;
        console.log("details"+this.userprofile);
       
      }
    )
  }
  close(){
   
      window.location.reload();
 
  }

}
