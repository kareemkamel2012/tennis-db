import { Component } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-player',
  templateUrl: './update-player.component.html',
  styleUrls: ['./update-player.component.css']
})
export class UpdatePlayerComponent {
  player: Player = new Player();
  constructor (private playerService: PlayerService, private router: Router) {}

  ngOnInit(): void {

  }

  updatePlayer() {
    this.playerService.updatePlayer(this.player).subscribe( data => {
      console.log(data);
    },
    error => console.log(error));
  }

  goToPlayerList(){
    this.router.navigate(['/players']);
  }

  onSubmit(): void {
    console.log(this.player);
    this.updatePlayer();
  }
}