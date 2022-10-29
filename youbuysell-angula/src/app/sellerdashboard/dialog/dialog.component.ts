import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { sellProduct } from 'src/app/sellproduct';
import { ServiceService } from 'src/app/service/service.service';
import { AuthServiceService } from '../../service/authentication.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  sellerId : any;
  categories:string[]=['electronics','homeappliances','mobile','laptop','furnitures','car','bike','Scooters','CommercialVehicles','homes'];
  product :sellProduct=new sellProduct();
  constructor(private  dialogRef:  MatDialogRef<DialogComponent>, @Inject(MAT_DIALOG_DATA) public  data:  any,private service:ServiceService,private authservice: AuthServiceService) {
  }

 

  ngOnInit(): void {
  }
  sell(product:any) {
     this.sellerId= this.authservice.getMymailid()
    this.product.sellerid=this.sellerId;
    this.service.addProducts(this.product).subscribe(
      (res:any )=>{
      console.log(res);
      alert("Product added"+res);
      }
     )
     this.dialogRef.close();
     window.location.reload();
    }
}



