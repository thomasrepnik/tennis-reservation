export * from './fields.service';
import { FieldsService } from './fields.service';
export * from './reservationTypes.service';
import { ReservationTypesService } from './reservationTypes.service';
export * from './reservations.service';
import { ReservationsService } from './reservations.service';
export * from './user.service';
import { UserService } from './user.service';
export const APIS = [FieldsService, ReservationTypesService, ReservationsService, UserService];
