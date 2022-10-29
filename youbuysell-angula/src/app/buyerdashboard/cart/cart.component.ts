import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { ServiceService } from 'src/app/service/service.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit {
  public product : any=[];
  public grandTotal !: number;
 
  
   
   constructor(private productservice: ServiceService) { 
    
    }
   

  ngOnInit(): void {
      this.productservice.getcartitem().subscribe(
          (res)=>{
            this.product=res;
          }
          )
  }
  remove(item:any){
    this.productservice.deletcartitem(item.transactionid).subscribe(
      (res)=>{},()=>{
        alert("the product is successfully deleted"); 
      }
    )
   window.location.reload();
  }

}
