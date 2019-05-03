import { Component, OnInit, forwardRef, Input } from '@angular/core';
import { UserService } from '../reservation-edit/user.service';
import { Observable } from 'rxjs';
import { UserSlim } from 'src/app/models/user';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, FormGroup, FormControl, NG_VALIDATORS, Validator, AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-player-select',
  templateUrl: './player-select.component.html',
  styleUrls: ['./player-select.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => PlayerSelectComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => PlayerSelectComponent),
      multi: true
    }
  ]
})
export class PlayerSelectComponent implements OnInit, ControlValueAccessor, Validator {

  @Input() title: string;

  public playerForm: FormGroup = new FormGroup(
    {
      guestName: new FormControl(null),
      playerId: new FormControl(null),
      isGuest: new FormControl(null)
    },
    this.dataCombinationValidator
  );

  users: Observable<UserSlim[]>;



  constructor(private userService: UserService) { }

  ngOnInit() {
    this.users = this.userService.getAllUsersWithoutMe();
    this.playerForm.updateValueAndValidity();

  }


  dataCombinationValidator(group: FormGroup): any {
    if (group) {
      if (group.value['isGuest'] === true && (group.value['guestName'] === "" || group.value['guestName'] == null)) {
        return { guestNameNotSet: true };
      } else if ((group.value['isGuest'] === false || group.value['isGuest'] == null) && group.value['playerId'] == null) {
        return { playerNotSet: true };
      }
    }

    return null;
  }

  onTouched = () => { }

  writeValue(val: any): void {
    console.log("write value " + JSON.stringify(val))
    val && this.playerForm.patchValue(val, { emitEvent: false });
  }

  registerOnChange(fn: any): void {
    console.log("on change registered: " + fn);
    this.playerForm.valueChanges.subscribe(fn);
  }

  registerOnTouched(fn: any): void {
    console.log("on touched registered");
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.playerForm.disable() : this.playerForm.enable();
  }

  validate(control: AbstractControl): ValidationErrors {
    console.log("Player validation", control);
    return this.playerForm.valid ? null : { invalidForm: { valid: false, message: "PlayerForm fields are invalid" } };
  }
}

