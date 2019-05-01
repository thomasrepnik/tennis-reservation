import { Component, OnInit, ViewChild } from '@angular/core';
import { BsLocaleService, BsDatepickerDirective } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { deLocale } from 'ngx-bootstrap/locale';
import { ReservationService } from '../reservation.service';
import * as moment from 'moment';


@Component({
  selector: 'app-timetable-header',
  templateUrl: './timetable-header.component.html',
  styleUrls: ['./timetable-header.component.scss']
})
export class TimetableHeaderComponent implements OnInit {

  @ViewChild('datepicker')
  private _picker: BsDatepickerDirective;

  date: Date;

  constructor(private bsLocaleService: BsLocaleService, private reservationService: ReservationService) { }

  ngOnInit() {
    defineLocale('de', deLocale)
    this.bsLocaleService.use('de')
    this.selectDate(new Date())
  }

  subtractDay() {
    let newDate = moment(this.date)
    newDate.subtract(1, 'days')
    this.selectDate(newDate.toDate())
  }

  addDay() {
    let newDate = moment(this.date)
    newDate.add(1, 'days')
    this.selectDate(newDate.toDate())
  }

  selectDate(newDate: Date) {
    this.date = newDate;
    this.dateChanged();
  }

  dateChanged() {
    this.reservationService.dateSelected.next(this.date);
  }

  onOpenCalendar(event) {
    const dayHoverHandler = event.dayHoverHandler;
    const hoverWrapper = (hoverEvent) => {
      const { cell, isHovered } = hoverEvent;

      if ((isHovered &&
        !!navigator.platform &&
        /iPad|iPhone|iPod/.test(navigator.platform)) &&
        'ontouchstart' in window
      ) {
        (this._picker as any)._datepickerRef.instance.daySelectHandler(cell);
      }

      return dayHoverHandler(hoverEvent);
    };
    event.dayHoverHandler = hoverWrapper;
  }

}
