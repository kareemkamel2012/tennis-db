import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Player } from './player';


@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private baseURL = "http://localhost:8090/";

  constructor(private httpClient: HttpClient) { }

  getPlayerList(): Observable<Player[]>{
    return this.httpClient.get<Player[]>(this.baseURL + "all")
  }

  getPlayerById(id: number): Observable<Object>{
    return this.httpClient.get(this.baseURL + "id/" + id)
  }

  getPlayerByName(name: string) : Observable<Object>{
    return this.httpClient.get(this.baseURL + "name/" + name)
  }

  getPlayerByRanking(ranking: number) : Observable<Object>{
    return this.httpClient.get(this.baseURL + "ranking/" + ranking)
  }

  addPlayer(player: Player): Observable<Object>{
    return this.httpClient.post(this.baseURL + "add", player)
  }

  updatePlayer(player: Player): Observable<Object>{
    return this.httpClient.put(this.baseURL + "update", player)
  }

  deletePlayerById(id: number): Observable<Object> {
    return this.httpClient.delete(this.baseURL + "delete/" + id)
  }
}

