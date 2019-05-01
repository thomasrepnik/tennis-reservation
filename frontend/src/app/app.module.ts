import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TimetableComponent } from './timetable/timetable.component';
import { TimetableHeaderComponent } from './timetable/timetable-header/timetable-header.component';
import { TimetableDetailComponent } from './timetable/timetable-detail/timetable-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReservationService } from './timetable/reservation.service';
import { ReservationEditComponent } from './timetable/reservation-edit/reservation-edit.component';
import { DatePipe } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { AuthenticationService } from './login/authentication.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NavigationComponent } from './navigation/navigation/navigation.component';
import { HeaderLayoutComponent } from './layouts/header-layout/header-layout.component';
import { HeadlessLayoutComponent } from './layouts/headless-layout/headless-layout.component';
import { NgxBootstrapSwitchModule } from 'ngx-bootstrap-switch';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NgSelectModule } from '@ng-select/ng-select';
import { PlayerSelectComponent } from './timetable/player-select/player-select.component';
import { PlayerPipe } from './pipes/player.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TimetableComponent,
    TimetableHeaderComponent,
    TimetableDetailComponent,
    ReservationEditComponent,
    LoginComponent,
    NavigationComponent,
    HeaderLayoutComponent,
    HeadlessLayoutComponent,
    PlayerSelectComponent,
    PlayerPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(),
    HttpClientModule,
    NgSelectModule,
    NgxBootstrapSwitchModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [
    ReservationService,
    DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
