import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { ReservationModel } from '../reservation.model';
import { Router } from '@angular/router';
import { CourtModel } from '../court.model';
import { Feld } from 'src/app/models/feld';
import { Reservation } from 'src/app/models/reservation';

@Component({
  selector: 'app-timetable-detail',
  templateUrl: './timetable-detail.component.html',
  styleUrls: ['./timetable-detail.component.scss']
})
export class TimetableDetailComponent implements OnInit {

  date: Date = new Date();
  courts: CourtModel[];
  timeslots: { from: string, to: string }[];
  reservations: Reservation[] = [];
  groupedReservations: Map<number, Reservation[]>;

  constructor(private reservationService: ReservationService, private router: Router) { }

  ngOnInit() {

    this.reservationService.getReservations(new Date()).subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
        this.groupedReservations = this.groupBy(reservations, reservation => reservation.feld.id);
      }
    );

    this.reservationService.getCourts().subscribe(
      (felder: CourtModel[]) => {
        this.courts = felder
      }
    );

    this.timeslots = this.reservationService.getTimeslots();

    this.reservationService.dateSelected.subscribe(
      (date: Date) => {
        this.date = date;

        this.reservationService.getReservations(date).subscribe(
          (reservations: Reservation[]) => {
            this.reservations = reservations;
            this.groupedReservations = this.groupBy(reservations, reservation => reservation.feld.id);
          }
        );
      }
    )

    this.reservationService.felderReceived.subscribe(
      (felder: CourtModel[]) => {

      }
    )
  }

  onCellClick(reservation: ReservationModel) {
    if (reservation.id > 0) {
      this.router.navigate(['reservation', reservation.id]);
    } else {
      this.router.navigate(['reservation'], { state: reservation })
    }
  }

  getReservationsByCourt(courtId: number): Reservation[] {
    let result: Reservation[] = [];

    this.timeslots.forEach((timeslot, index) => {
      const reservation = this.reservations.filter(
        reservation => {
          return reservation.feld.id === courtId && reservation.start === timeslot.from;
        })

      if (reservation.length > 0) {
        result.push(reservation[0])
      } else {
        result.push(null)
      }
    })


    return result;
  }

  private groupBy(list: Reservation[], keyGetter: any) {
    const map = new Map();
    list.forEach((item) => {
      const key = keyGetter(item);
      const collection = map.get(key);
      if (!collection) {
        map.set(key, [item]);
      } else {
        collection.push(item);
      }
    });
    return map;
  }

}
