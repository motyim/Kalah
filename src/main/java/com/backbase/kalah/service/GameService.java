package com.backbase.kalah.service;

import com.backbase.kalah.dto.GamePlayResponse;
import com.backbase.kalah.dto.GameResponse;
import com.backbase.kalah.exception.GameException;
import com.backbase.kalah.model.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class GameService {

    private HashMap <Integer , Game> map ;

    @Value("${kalah.uri}")
    private String URI ;

    GameService(){
        map = new HashMap<>();
    }

    public GameResponse createGame(){

        int gameId = generateGameID();
        map.put(gameId , new Game());

        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(gameId);
        gameResponse.setUri(URI+gameId);

        return gameResponse;
    }

    public GamePlayResponse play(int gameId, int pitId){
        if(!map.containsKey(gameId))
            throw new GameException("No Game with Id " + gameId);
        Game game = map.get(gameId);

        if(pitId > 14 ||  pitId < 1)
            throw new GameException("Invalid move with pitId "+ pitId);
        game.play(pitId-1);

        GamePlayResponse response = new GamePlayResponse();
        response.setId(gameId);
        response.setUri(URI+gameId);
        response.setStatus(game.getHouses());
        return response;
    }

    private int generateGameID() {
        int gameID;
        do{
            gameID = new Random().nextInt(999);
        }while(map.containsKey(gameID));
        return gameID;
    }


}
