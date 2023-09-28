import { Component } from '@angular/core';
import { Player } from '../player'
import { PlayerService } from '../player.service';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css'],
})
export class PlayerListComponent {

    players: Player[];
    controls: FormArray;
    isEdit = new Map();

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
        this.isEdit.set(player.name, false)
      }
    }

    changeEdit(player : Player) {
      console.log("changing from " + this.isEdit + " to " + !this.isEdit);
      this.isEdit.set(player.name, !this.isEdit.get(player.name));
    }
}
