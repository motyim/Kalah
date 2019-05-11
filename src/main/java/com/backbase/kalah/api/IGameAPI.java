package com.backbase.kalah.api;

import com.backbase.kalah.dto.GamePlayResponse;
import com.backbase.kalah.dto.GameResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;



@Validated
@Api(value = "GameEngine API")
public interface IGameAPI {

    @PostMapping("games")
    @ApiOperation(value = "Create GameEngine", nickname = "CreateGame", notes = "Create new game and get its unique id")
    ResponseEntity<GameResponse> createGame();

    @PutMapping("games/{gameId}/pits/{pitId}")
    @ApiOperation(value = "Play GameEngine", nickname = "PlayGame", notes = "play a turn in game using game id and house pit id")
    ResponseEntity<GamePlayResponse> playGame(@PathVariable @Min(1) @Max(999) int gameId,
                                                     @PathVariable @Min(1) @Max(13) int pitId);
}
