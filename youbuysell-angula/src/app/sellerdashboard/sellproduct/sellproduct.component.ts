import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { sellProduct } from 'src/app/sellproduct';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { ServiceService } from 'src/app/service/service.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-sellproduct',
  templateUrl: './sellproduct.component.html',
  styleUrls: ['./sellproduct.component.css']
})
export class SellproductComponent implements OnInit {
  product : sellProduct=new sellProduct();
  products : Array<sellProduct>=[];
  sellerid: any;
  constructor(private  dialog:  MatDialog,private service:ServiceService,private authservice:AuthServiceService) {
    this.sellerid= this.authservice.getMymailid();
    this.service.getProductById(this.sellerid).subscribe(
      (res:any) =>
    {
      console.log(res);
      this.products=res;
    });
   }
  sell(){
          this.dialog.open(DialogComponent,{
            width:'600px'
          });
  }

  ngOnInit(): void {
  }
  delete(productid:string)
  {
    if(confirm('Are you sure to delete?'))
    {
    this.service.deleteProduct(productid).subscribe(
      (res:any)=>
       {
         console.log(res);
         alert("product removed");
      }
    )
  }
  window.location.reload();
  }
  relode(){
    // window.location.reload();
  }
  }

 

