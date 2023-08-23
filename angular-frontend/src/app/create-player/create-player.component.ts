import { Component } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-player',
  templateUrl: './create-player.component.html',
  styleUrls: ['./create-player.component.css']
})
export class CreatePlayerComponent {
  player: Player = new Player();
  constructor (private playerService: PlayerService, private router: Router) {}

  ngOnInit(): void {

  }

  savePlayer() {
    this.playerService.addPlayer(this.player).subscribe( data => {
      console.log(data);
    },
    error => console.log(error));
  }

  goToPlayerList(){
    this.router.navigate(['/players']);
  }

  onSubmit(): void {
    console.log(this.player);
    this.savePlayer();
  }
}
