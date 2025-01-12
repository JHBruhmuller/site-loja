import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProductCreateComponent } from './product-create/product-create.component';
import { ProductListComponent } from './product-list/product-list.component';
import { SalesCreateComponent } from './sales-create/sales-create.component';
import { CashOperationsComponent } from './cash-operations/cash-operations.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'sdsd', component: ProductListComponent },
  { path: 'products', component: ProductCreateComponent },
  { path: 'sales/create', component: SalesCreateComponent },
  { path: 'cash-operations', component: CashOperationsComponent },
];
