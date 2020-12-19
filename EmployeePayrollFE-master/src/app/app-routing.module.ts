import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateEmployeeComponent } from './employee-component/create-employee/create-employee.component';
import { EmployeeDashboardComponent } from './employee-component/create-employeedashboard/employee-dashboard/employee-dashboard.component';


const routes: Routes = [
   {path:"",component: EmployeeDashboardComponent},
   {path:"create-employee",component:CreateEmployeeComponent},
   {path:"create/:id",component:CreateEmployeeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
