import { Component, OnInit } from '@angular/core';

import { Product } from 'src/app/product';
import { AuthServiceService } from 'src/app/service/authentication.service';
import { RouterService } from 'src/app/service/router.service';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
public productList : any ;
public filterCategory : any
searchKey:string ="";
productitem :Product= new Product();
emailid: any;
constructor(private productservice :ServiceService,private authservice:AuthServiceService,
  private router:RouterService) { }

ngOnInit(): void {
  this.productservice.getProducts()
  .subscribe(res=>{
     this.productList = res;
    this.filterCategory = res;
   
  });
  
  this.productservice.search.subscribe(
    (res:any)=>{
      this.searchKey=res;
    }
  )
}

showintest(item:any){
  let mytoken=this.authservice.getMytoken();
  this.emailid= this.authservice.getMymailid();
if(mytoken==null){
  this.router.openlogin();
}else{
 
  this.productitem.transactionid=item.productid+this.emailid; 
  this.productitem.productid=item.productid;
  this.productitem.sellerid=item.sellerid;
  this.productitem.buyerid=this.emailid;
  this.productitem.title=item.producttitle;
  this.productitem.price=item.price;
  this.productitem.productdiscription=item.productdescription;
  this.productitem.image=item.productimage;
  this.productservice.addtointest(this.productitem).subscribe(
    (res)=>{
      if(res){
       
      alert("product added to cart");
     
      }    
    }),(err:any)=>{
      alert("product present in cart");
    }
      
    window.location.reload();  
}
}

filterproduct(category:any){
  this.filterCategory=this.productList
  .filter((item:any)=>
  {
    if(item.category==category||category==''){
      return item;
    }
  }
  )
  
}
}
