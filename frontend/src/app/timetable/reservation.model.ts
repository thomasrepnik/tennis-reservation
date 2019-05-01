import { from } from 'rxjs';

export class ReservationModel {

    id: number;
    datum: Date;
    feld: string
    start: string;
    ende: string;
    spieler1: number;
    spieler2: number;
    spieler3: number;
    spieler4: number;
    gast1: string;
    gast2: string;
    gast3: string;
    gast4: string;
    reservationType: string;

    constructor() { }

}