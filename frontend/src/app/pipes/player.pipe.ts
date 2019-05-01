import { Pipe, PipeTransform } from '@angular/core';
import { Spieler } from '../models/reservation';


@Pipe({
  name: 'player'
})
export class PlayerPipe implements PipeTransform {

  transform(value: Spieler, guest: String): any {
    if (value != null && value.id > 0) {
      return value.firstName + " " + value.lastName;
    } else {
      return guest
    }
  }

}
