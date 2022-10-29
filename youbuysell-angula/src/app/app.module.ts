import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pagelayout/login/login.component';
import { FooterComponent } from './pagelayout/footer/footer.component';
import { CartComponent } from './buyerdashboard/cart/cart.component';
import { ProductComponent } from './buyerdashboard/product/product.component';
import { HeaderComponent } from './buyerdashboard/header/header.component';

import {MatCardModule} from '@angular/material/card';
import {Routes,RouterModule } from '@angular/router';
import {MatTabsModule} from '@angular/material/tabs';
import {MatInputModule} from '@angular/material/input';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDividerModule} from '@angular/material/divider';
import {MatMenuModule} from '@angular/material/menu';
import {MatDialogModule} from '@angular/material/dialog';
import {FlexLayoutModule} from '@angular/flex-layout';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatIconModule} from '@angular/material/icon';
import { FilterPipe } from './sheard/filter.pipe';
import { IntrestedComponent } from './sellerdashboard/intrested/intrested.component';
import { CanactivateGuard } from './service/canactivate.guard';
import { SellproductComponent } from './sellerdashboard/sellproduct/sellproduct.component';
import { DialogComponent } from './sellerdashboard/dialog/dialog.component';
import { EditprofileComponent } from './updateuser/editprofile/editprofile.component';


const router: Routes=[
  {
    path:'',redirectTo:'products',pathMatch:'full'
  },
  {
    path:'login',component:LoginComponent
  },
  { 
    path:'products', component:ProductComponent
  },
  {
    path:'cart', component:CartComponent,
    canActivate: [CanactivateGuard]
  },
  {
    path:'sell',component:SellproductComponent,
    canActivate: [CanactivateGuard]
  },
  {
    path:'intrested',component:IntrestedComponent, 
    canActivate: [CanactivateGuard]
  }
 



]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FooterComponent,
    CartComponent,
    ProductComponent,
    HeaderComponent,
    FilterPipe,
    IntrestedComponent,
    SellproductComponent,
    DialogComponent,
    EditprofileComponent,

   
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCardModule,
    RouterModule,RouterModule.forRoot(router),
    MatTabsModule,MatInputModule,FormsModule,ReactiveFormsModule,HttpClientModule,MatIconModule,
    MatToolbarModule,MatDividerModule,MatMenuModule,MatDialogModule,FlexLayoutModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
