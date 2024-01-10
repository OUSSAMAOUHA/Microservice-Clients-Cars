import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { TotpComponent } from './totp/totp.component';
import { OrderComponent } from './order/order.component';
import { TokenComponent } from './register/token.component';
import { ProductListComponent } from './voitures/list.component';
import { ProductViewComponent } from './voitures/view.component';
import { ProductAddEditComponent } from './voitures/add-edit.component';
import {ClientAddEditComponent} from './clients/add-edit.component';
import {ClientViewComponent} from './clients/view.component';
import {ClientListComponent} from './clients/list.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'totp', component: TotpComponent },
  { path: 'order', component: OrderComponent },
  { path: 'verify', component: TokenComponent },
  { path: 'voitures', redirectTo: 'voitures/list', pathMatch: 'full'},
  { path: 'voitures/list', component: ProductListComponent },
  { path: 'voitures/:id/view', component: ProductViewComponent },
  { path: 'voitures/add', component: ProductAddEditComponent },
  { path: 'voitures/:id/edit', component: ProductAddEditComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'clients', redirectTo: 'clients/list', pathMatch: 'full'},
  { path: 'clients/list', component: ClientListComponent },
  { path: 'clients/:id/view', component: ClientViewComponent },
  { path: 'clients/add', component: ClientAddEditComponent },
  { path: 'clients/:id/edit', component: ClientAddEditComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
