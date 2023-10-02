import { Component } from '@angular/core';
import { Player } from '../player'
import { PlayerService } from '../player.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css'],
})
export class PlayerListComponent {

    players: Player[];
    isEdit = new Map();
    names = new Map();

    constructor(private playerService: PlayerService) {}

    ngOnInit(): void {
      this.getPlayers();
      this.initializeMaps();
    }

    private getPlayers(){
      this.playerService.getPlayerList().subscribe(data => {
        this.players = data;
      });
    }

    private initializeMaps() {
      for (let player of this.players) {
        this.isEdit.set(player.id, false)
        this.names.set(player.id, new FormControl(player.name))
      }
    }

    private updatePlayer(player: Player) {
      let name = <HTMLInputElement>document.getElementById("name" + player.id)
      let ranking = <HTMLInputElement>document.getElementById("ranking" + player.id)
      if (name != null && ranking != null) {
        player.name = name.value
        player.ranking = parseInt(ranking.value)
        this.playerService.updatePlayer(player).subscribe( data => {
          console.log(data);
        },
        error => console.log(error));
      }
    }

    private deletePlayerById(id: number) {
      this.playerService.deletePlayerById(id).subscribe( data => {
        console.log(data);
      },
      error => console.log(error));
    }

    edit(player: Player) {
      if (this.isEdit.get(player.id) == true) {
        console.log(player.id)
        this.updatePlayer(player)
      }
      this.isEdit.set(player.id, !this.isEdit.get(player.id));
    }

    deleteButton(player: Player) {
      this.deletePlayerById(player.id)
      this.getPlayers();
      this.deletePlayerById(player.id)
      this.getPlayers();
    }
}
