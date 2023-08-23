import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlayerListComponent } from './player-list/player-list.component';
import { FormsModule } from '@angular/forms';
import { CreatePlayerComponent } from './create-player/create-player.component';
import { UpdatePlayerComponent } from './update-player/update-player.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    CreatePlayerComponent,
    UpdatePlayerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
