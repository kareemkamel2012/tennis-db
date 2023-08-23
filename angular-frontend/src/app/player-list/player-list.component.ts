import { Component } from '@angular/core';
import { Player } from '../player'
import { PlayerService } from '../player.service';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent {

    players: Player[];

    constructor(private playerService: PlayerService) {}

    ngOnInit(): void {
      this.getPlayers();
    }

    private getPlayers(){
      this.playerService.getPlayerList().subscribe(data => {
        this.players = data;
      });
    }
}
