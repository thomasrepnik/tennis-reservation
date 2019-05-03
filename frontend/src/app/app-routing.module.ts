import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReservationEditComponent } from './timetable/reservation-edit/reservation-edit.component';
import { TimetableComponent } from './timetable/timetable.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { HeaderLayoutComponent } from './layouts/header-layout/header-layout.component';
import { componentFactoryName } from '@angular/compiler';
import { HeadlessLayoutComponent } from './layouts/headless-layout/headless-layout.component';
import { ReservationsComponent } from './reservations/reservations/reservations.component';
import { NewsComponent } from './news/news/news.component';

const routes: Routes = [
  {
    path: '',
    component: HeaderLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'reservations',
        component: ReservationsComponent
      },
      {
        path: 'news',
        component: NewsComponent
      },
      {
        path: 'reservation',
        component: ReservationEditComponent
      },
      {
        path: 'reservation/:id',
        component: ReservationEditComponent
      },
      {
        path: '',
        component: TimetableComponent
      }
    ]
  },
  {
    path: '',
    component: HeadlessLayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
