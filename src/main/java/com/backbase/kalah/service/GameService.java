package com.backbase.kalah.service;

import com.backbase.kalah.dto.GamePlayResponse;
import com.backbase.kalah.dto.GameResponse;
import com.backbase.kalah.model.Game;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

@Service
public class GameService {

    private HashMap <Integer , Game> map ;
    private final static String URI = "http://localhost:8080/games/";

    GameService(){
        System.out.println("new noe");
        map = new HashMap<Integer, Game>();
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
        Game game = map.get(gameId);
        game.play(pitId);

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
