import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { map } from 'rxjs/operators'
import { Observable } from 'rxjs';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

import { ReservationModel } from '../reservation.model';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ReservationService } from '../reservation.service';
import { UserService } from './user.service';
import { UserSlim } from 'src/app/models/user';
import { Reservation, Spieler } from 'src/app/models/reservation';
import { ReservationType } from 'src/app/models/reservation-type';

@Component({
  selector: 'app-reservation-edit',
  templateUrl: './reservation-edit.component.html',
  styleUrls: ['./reservation-edit.component.scss']
})
export class ReservationEditComponent implements OnInit {

  modalRef: BsModalRef;
  reservation: Reservation;
  reservationForm: FormGroup;
  editMode: boolean;
  users: Observable<UserSlim[]>;
  reservationTypes: Observable<any[]>;

  @ViewChild('myForm')
  myForm: any;



  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private reservationService: ReservationService,
    private router: Router,
    private modalService: BsModalService,
    private userService: UserService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.reservationForm = new FormGroup({
      'date': new FormControl(null, Validators.required),
      'time': new FormControl(null, Validators.required),
      'spielart': new FormControl(null, Validators.required),
      'players': new FormArray(
        []
      )
    })

    this.reservationForm.get('spielart').valueChanges.subscribe(
      (val: ReservationType) => {
        const formArray = (<FormArray>this.reservationForm.get('players'));

        if (val.anzahlspieler < formArray.length) {
          //Spieler werden entfernt
          for (let i = formArray.length; i > val.anzahlspieler; i--) {
            formArray.removeAt(formArray.length - 1);
          }
        } else {
          //Spieler werden hinzugefügt
          const length = formArray.length;
          for (let i = 0; i < (val.anzahlspieler - length); i++) {
            formArray.push(new FormControl(null));
          }
        }
      }
    )

    //Init Players
    const control = new FormControl(null);
    (<FormArray>this.reservationForm.get('players')).push(new FormControl(null));
    (<FormArray>this.reservationForm.get('players')).push(new FormControl(null));
    (<FormArray>this.reservationForm.get('players')).push(new FormControl(null));
    (<FormArray>this.reservationForm.get('players')).push(new FormControl(null));


    this.users = this.userService.getAllUsersWithoutMe();
    this.reservationTypes = this.reservationService.getReservationTypes();

    this.activatedRoute.params.subscribe(
      (params: Params) => {
        if (params['id'] !== null && params['id'] !== undefined) {
          console.log(params['id'])
          const id: number = +params['id']
          this.reservationService.getReservationById(id).subscribe(
            (reservation: Reservation) => {
              this.reservation = reservation;
              this.updateForm(this.reservation);
            }
          );
        }
      }
    )

    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state)).subscribe(
        (reservationModel: any) => {
          if (reservationModel.hasOwnProperty('start')) {
            this.reservation = reservationModel;
            this.updateForm(this.reservation);
          }
        }

      )



  }

  asFormArray(input: any): FormArray {
    return input;
  }

  initPlayerItem() {
    return this.formBuilder.group({
      guestName: ["", []],
      playerId: ["", []],
      isGuest: ["", []]
    });
  }

  onSubmit() {

    const newReservation: Reservation = new Reservation();
    newReservation.id = null; // Wird automatisch upgedatet
    newReservation.datum = this.reservation.datum;
    newReservation.start = this.reservation.start;
    newReservation.ende = this.reservation.ende;
    newReservation.spieler1 = this.createSpieler(this.reservationForm.value['players'][0]);
    newReservation.spieler2 = this.createSpieler(this.reservationForm.value['players'][1]);
    newReservation.spieler3 = this.createSpieler(this.reservationForm.value['players'][2]);
    newReservation.spieler4 = this.createSpieler(this.reservationForm.value['players'][3]);
    newReservation.gast1 = this.createGast(this.reservationForm.value['players'][0]);
    newReservation.gast2 = this.createGast(this.reservationForm.value['players'][1]);
    newReservation.gast3 = this.createGast(this.reservationForm.value['players'][2]);
    newReservation.gast4 = this.createGast(this.reservationForm.value['players'][3]);
    newReservation.feld = this.reservation.feld;
    newReservation.reservationType = this.reservationForm.value['spielart']

    if (this.editMode) {
      //update
      newReservation.id = this.reservation.id;
      this.reservationService.updateReservation(newReservation).subscribe(
        (reservation: Reservation) => {
          console.log('Reservation wurde mit ID ' + reservation.id + ' aktualisiert')
          this.router.navigate(['']);
        }
      );;
    } else {
      this.reservationService.addReservation(newReservation);
    }


  }

  createSpieler(value: any): Spieler {
    if (value !== null && value !== undefined && value.playerId !== null && value.playerId !== undefined && value.playerId > 0) {
      return new Spieler(value.playerId, '', '')
    } else {
      return null;
    }
  }

  createGast(value: any): string {
    if (value !== null && value !== undefined && value.guestName !== null && value.guestName !== undefined) {
      return value.guestName
    } else {
      return null;
    }
  }

  onCancel() {
    this.router.navigate(['']);
  }

  onDelete(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, { class: 'modal-md' });
    return false;
  }

  confirm() {
    this.reservationService.deleteReservation(this.reservation.id);
    this.modalRef.hide();
    this.router.navigate(['']);
  }

  decline() {
    this.modalRef.hide();
  }


  private updateForm(reservationModel: Reservation) {
    if (reservationModel !== null) {
      this.reservationForm.patchValue({ 'date': this.datePipe.transform(reservationModel.datum, 'dd.MM.yyyy') })
      this.reservationForm.patchValue({ 'time': reservationModel.start + " - " + reservationModel.ende })

      this.reservationForm.patchValue({
        'players': [
          {
            guestName: reservationModel.spieler1 == null ? reservationModel.gast1 : "",
            playerId: reservationModel.spieler1 == null ? null : reservationModel.spieler1.id,
            isGuest: reservationModel.spieler1 == null
          },
          {
            guestName: reservationModel.spieler2 == null ? reservationModel.gast2 : "",
            playerId: reservationModel.spieler2 == null ? null : reservationModel.spieler2.id,
            isGuest: reservationModel.spieler2 == null
          },
          {
            guestName: reservationModel.spieler3 == null ? reservationModel.gast3 : "",
            playerId: reservationModel.spieler3 == null ? null : reservationModel.spieler3.id,
            isGuest: reservationModel.spieler3 == null
          },
          {
            guestName: reservationModel.spieler4 == null ? reservationModel.gast4 : "",
            playerId: reservationModel.spieler4 == null ? null : reservationModel.spieler4.id,
            isGuest: reservationModel.spieler4 == null
          }
        ]
      })

      if (reservationModel.reservationType != null) {
        this.reservationForm.patchValue({ 'spielart': reservationModel.reservationType })
      }

      this.editMode = (reservationModel.id > 0);
    } else {
      this.editMode = false;
    }
  }



}
