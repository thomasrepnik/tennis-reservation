import { Feld } from './feld';
import { ReservationType } from './reservation-type';

export class Spieler {
    id: number;
    firstName: string;
    lastName: string;
}


export class Reservation {
    id: number;
    datum: string;
    feld: Feld;
    start: string;
    ende: string;
    spieler1: Spieler;
    spieler2: Spieler;
    spieler3: Spieler;
    spieler4: Spieler;
    gast1: string;
    gast2: string;
    gast3: string;
    gast4: string;
    reservationType: ReservationType;
}