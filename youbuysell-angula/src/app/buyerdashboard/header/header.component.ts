import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { RouterService } from 'src/app/service/router.service';
import { ServiceService } from 'src/app/service/service.service';
import { EditprofileComponent } from 'src/app/updateuser/editprofile/editprofile.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public totalItem : number = 0;
  public searchTerm !:string;
  public userprofile : any=[];
  
  constructor(private productservice:ServiceService,
    private authservice:AuthServiceService,private router:RouterService,private dialog: MatDialog) { 
      
  
  }

ngOnInit(): void {
     this.productservice.getcartitem().subscribe(
    (res)=>{
      this.totalItem=res.length;
    }
  )
  this.view()

}
search(event:any){
  this.searchTerm = (event.target as HTMLInputElement).value;
  console.log(this.searchTerm);
  this.productservice.search.next(this.searchTerm);

}

login(){
  this.router.openlogin();
}
logout(){
  this.authservice.logout();
  window.location.reload(); 
  this.router.openproduct();
}
isNoteView = true;
view(){
  let token=this.authservice.getMytoken()
  if(token==null)
  {
  
  this.isNoteView=false;
  }
  else
  {
    
    this.isNoteView=true

  }
 
}
viewbuyerdetails(){
this.dialog.open(EditprofileComponent);
}
close(){
  window.location.reload();
}

}

 