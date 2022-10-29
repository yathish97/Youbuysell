import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { ServiceService } from 'src/app/service/service.service';


@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {
 public userprodile:any
 public user:any
 isupdate = true;
 
  constructor(private dialogref:MatDialogRef<EditprofileComponent>, @Inject(MAT_DIALOG_DATA)public data:any,private service:ServiceService,private authservice:AuthServiceService) { 
   let emailid= this.authservice.getMymailid();
    this.service.viewprofile(emailid).subscribe(
      (res:any)=>{
        this.userprodile=res;
  
      }
    )
  }

  ngOnInit(): void {
    this.dialogref.updatePosition({top:`70px`,right:`20px`});
  }
  upadte(detail:any){
    this.service.updateprofile(detail).subscribe(
      (res:any)=>{
this.user=res;
       
      }
    )

    this.dialogref.close();
    window.location.reload();
  }

  view(){
      if(this.isupdate)
      {
       this.isupdate=false;
      }
}
}






