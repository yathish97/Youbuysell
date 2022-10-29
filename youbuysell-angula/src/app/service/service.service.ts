
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Product } from '../product';
import { sellProduct } from '../sellproduct';
import { User } from '../user';
import { AuthServiceService } from './authentication.service';


@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  public search = new BehaviorSubject<string>("");
  cartItemList: Array<Product> = [];
  Userlist: Array<User>=[];
  sellproductobj: sellProduct = new sellProduct();
  productList = new BehaviorSubject<Array<Product>>([]);
  Userupdate = new BehaviorSubject<Array<User>>([]);



  mytoken = this.authserve.getMytoken();
  buyerid = this.authserve.getMymailid();
  constructor(private httpcli: HttpClient, private authserve: AuthServiceService) { }

  getcartitem(): Observable<Array<Product>> {
    return this.httpcli.get<Product>(`http://localhost:8060/Cart/viewproduct/${this.buyerid}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
      }).pipe(
        tap((addednote: any) => {
          this.cartItemList.push(addednote);
          this.productList.next(this.cartItemList);
        }))
  }
  addtointest(note: Product): Observable<Product> {
    return this.httpcli.post<Product>('http://localhost:8060/Cart/saveproduct', note,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
      })
  }

  deletcartitem(id: any): Observable<any> {
    return this.httpcli.delete<any>(`http://localhost:8060/Cart/delete/${id}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
      })

  }
  getbysellerid(sellerid: any): Observable<Array<Product>> {
    return this.httpcli.get<Product>(`http://localhost:8060/Cart/viewbyseller/${sellerid}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
      }).pipe(
        tap((addednote: any) => {
          this.cartItemList.push(addednote);
          this.productList.next(this.cartItemList);
        }))

  }
  getProducts(): Observable<sellProduct> {
    return this.httpcli.get<sellProduct>("http://localhost:9093/Sell/product/viewproducts", {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
    }).pipe(
      tap((addednote: any) => {
        this.cartItemList.push(addednote);
        this.productList.next(this.cartItemList);
      }))
  }
  addProducts(product: sellProduct): Observable<Product> {
    return this.httpcli.post<Product>("http://localhost:9093/Sell/product/addproduct", product, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
    })
  }
  deleteProduct(productid: any): Observable<any> {
    return this.httpcli.delete<any>(`http://localhost:9093/Sell/delete/${productid}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
    })
  }
  getProductById(sellerid: sellProduct): Observable<sellProduct> {
    return this.httpcli.get<sellProduct>(`http://localhost:9093/Sell/product/viewbysid/${sellerid}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.mytoken}`)
    }).pipe(
      tap((addednote: any) => {
        this.cartItemList.push(addednote);
        this.productList.next(this.cartItemList);
      }))
  }
  viewprofile(emailid:any) : Observable<User>
{
  
  return this.httpcli.get<User>(`http://localhost:9096/authentication/viewuser/${emailid}`);
}
// updateprofile(profileobj:User){
//  console.log("email id update service "+profileobj.emailid);
//  return this.httpcli.put<User>(`http://localhost:9096/authentication/updateuser/${profileobj.emailid}`, profileobj);
 
// }

updateprofile(profileobj : User)
          {
           let mtoken=this.authserve.getMytoken();
           return this.httpcli.put<User>(`http://localhost:9096/authentication/updateuser/${profileobj.emailid}`,profileobj, 
           {
             headers : new HttpHeaders().set('Authorization',`Bearer ${mtoken}`)
           }
           ).pipe
           (
           tap( (updemp : User)=>
                   {
         let existobj = this.Userlist.find(emp=>emp.emailid==updemp.emailid);
         Object.assign(existobj,updemp);
         this.Userupdate.next(this.Userlist);
                   }
           )
           )
                   }

}
