import { from } from 'rxjs';

export class CourtModel {

    id: number;
    name: string;

    constructor(id: number, name: string) {
        this.id = id;
        this.name = name;
    }

}