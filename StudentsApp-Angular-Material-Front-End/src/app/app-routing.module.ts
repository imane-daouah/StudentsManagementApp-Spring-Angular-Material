import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {StudentsComponent} from "./students/students.component";
import {PaymentsComponent} from "./payments/payments.component";
import {LoadPaymentsComponent} from "./load-payments/load-payments.component";
import {LoadStudentsComponent} from "./load-students/load-students.component";
import {AdminTempleteComponent} from "./admin-templete/admin-templete.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";

const routes: Routes = [
  {path : "login", component:LoginComponent},
  {path : "admin", component:AdminTempleteComponent,canActivate:[AuthGuard],
    children:[
      {path : "home", component:HomeComponent} ,
      {path : "profile", component:ProfileComponent},
      {path : "login", component:LoginComponent},
      {path : "dashboard", component:DashboardComponent},
      {path : "students", component:StudentsComponent},
      {path : "payments", component:PaymentsComponent},
      {path : "loadPayments", component:LoadPaymentsComponent,
        canActivate:[AuthorizationGuard], data:{roles:['ADMIN']}},
      {path : "loadStudents", component:LoadStudentsComponent,
        canActivate:[AuthorizationGuard], data:{roles:['ADMIN']}},
      // pour acceder acette route va faire une verification si le foles et un admine
      // par authorizationguard

    ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
