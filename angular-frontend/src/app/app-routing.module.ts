import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerListComponent } from './player-list/player-list.component';
import { CreatePlayerComponent } from './create-player/create-player.component';
import { UpdatePlayerComponent } from './update-player/update-player.component';


const routes: Routes = [
  {path: 'players', component: PlayerListComponent},
  {path: 'create-player', component: CreatePlayerComponent},
  {path: 'update-player', component: UpdatePlayerComponent},
  {path: '', redirectTo: 'players', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
