import { Injectable } from '@angular/core';
import { UserSlim } from 'src/app/models/user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getAllUsersWithoutMe(): Observable<UserSlim[]> {
    return this.httpClient.get<UserSlim[]>('/api/users')
  }

}
