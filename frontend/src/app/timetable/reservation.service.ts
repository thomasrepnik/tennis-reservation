import { ReservationModel } from './reservation.model';
import { Subject, Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { CourtModel } from './court.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Feld } from '../models/feld';
import { Reservation } from '../models/reservation';
import * as moment from 'moment';

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) { }

  dateSelected = new Subject<Date>();
  felderReceived = new Subject<CourtModel[]>();


  reservations: Reservation[] = [];

  getCourts(): Observable<CourtModel[]> {
    return this.httpClient.get<Feld[]>('/api/felds')
      .pipe(
        map(fields => {
          let courts: CourtModel[] = [];

          for (let field of fields) {
            courts.push(new CourtModel(field.id, field.name))
          }

          return courts;
        })
      )
  }

  getReservationTypes(): Observable<any[]> {
    return this.httpClient.get<any[]>('/api/reservation-types');
  }

  getTimeslots(): { from: string, to: string }[] {
    return [
      { from: '09:00', to: '10:00' },
      { from: '10:00', to: '11:00' },
      { from: '11:00', to: '12:00' },
      { from: '12:00', to: '13:00' },
      { from: '13:00', to: '14:00' },
      { from: '14:00', to: '15:00' },
      { from: '15:00', to: '16:00' },
      { from: '16:00', to: '17:00' },
      { from: '17:00', to: '18:00' },
      { from: '18:00', to: '19:00' }
    ]
  }

  getReservations(date: Date): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>('/api/reservations?date=' + moment(date).format('YYYY-MM-DD'));
  }

  getReservationById(id: number): Observable<Reservation> {
    return this.httpClient.get<Reservation>('/api/reservations/' + id);
  }

  addReservation(reservation: Reservation) {
    this.httpClient.post<Reservation>('/api/reservations/', reservation).subscribe(
      (reservation: Reservation) => {
        console.log('Reservation wurde mit ID ' + reservation.id + 'hinzugefügt')
      }
    );
  }

  updateReservation(reservation: Reservation) {
    let reservationToUpdate = this.reservations.find((r) => r.id === reservation.id);
    let index = this.reservations.indexOf(reservationToUpdate);
    //TODO: HTTP PUT CALL: this.reservations[index] = reservation;
  }

  deleteReservation(id: number) {
    let reservationToUpdate = this.reservations.find((r) => r.id === id);
    let index = this.reservations.indexOf(reservationToUpdate);
    //TODO: HTTP DELETE CALL: this.reservations.splice(index, 1);
  }

}
