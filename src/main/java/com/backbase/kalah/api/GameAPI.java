package com.backbase.kalah.api;

import com.backbase.kalah.dto.GamePlayResponse;
import com.backbase.kalah.dto.GameResponse;
import com.backbase.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameAPI {

    @Autowired
    GameService service;

    @PostMapping("games")
    public ResponseEntity<GameResponse> createGame(){
        GameResponse gameResponse = service.createGame();
        return ResponseEntity.status(HttpStatus.CREATED).body(gameResponse);
    }


    @PutMapping("games/{gameId}/pits/{pitId}")
    public ResponseEntity<GamePlayResponse> playGame(@PathVariable  int gameId, @PathVariable int pitId){
        GamePlayResponse response = service.play(gameId, pitId);
        return ResponseEntity.ok(response);
    }
}
