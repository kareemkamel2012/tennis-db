import { Component } from '@angular/core';
import { Player } from '../player'
import { PlayerService } from '../player.service';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent {

    players: Player[];

    controls: FormArray;

    constructor(private playerService: PlayerService) {}

    ngOnInit(): void {
      this.getPlayers();
      const toGroups = this.players.map(player => {
        return new FormGroup({
          name: new FormControl(player.name, Validators.required),
          ranking: new FormControl(player.ranking)
        });
      });
      this.controls = new FormArray(toGroups);
    }

    private getPlayers(){
      this.playerService.getPlayerList().subscribe(data => {
        this.players = data;
      });
    }
}
