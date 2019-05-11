package com.backbase.kalah.service;

import com.backbase.kalah.dto.GamePlayResponse;
import com.backbase.kalah.dto.GameResponse;
import com.backbase.kalah.exception.GameException;
import com.backbase.kalah.model.GameEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class GameService {

    private HashMap <Integer , GameEngine> map ;

    @Value("${app.uri}")
    private String URI ;

    GameService(){
        map = new HashMap<>();
    }

    public GameResponse createGame(){

        int gameId = generateGameID();
        map.put(gameId , new GameEngine());

        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(gameId);
        gameResponse.setUri(URI+gameId);

        return gameResponse;
    }

    public GamePlayResponse play(int gameId, int pitId){
        if(!map.containsKey(gameId))
            throw new GameException("No GameEngine with Id " + gameId);
        GameEngine game = map.get(gameId);

        if(pitId > 14 ||  pitId < 1)
            throw new GameException("Invalid move with pitId "+ pitId);
        game.play(pitId-1);

        GamePlayResponse response = new GamePlayResponse();
        response.setId(gameId);
        response.setUri(URI+gameId);
        Map<Integer,String> statusMap = mappingStatus(game.getHouses());
        response.setStatus(statusMap);
        return response;
    }

    private Map<Integer, String> mappingStatus(int[] houses) {
        Map<Integer,String> statusMap = new HashMap<>();
        IntStream.range(0,houses.length)
                .forEach(i-> statusMap.put(i+1,Integer.toString(houses[i])));
        return statusMap;
    }

    private int generateGameID() {
        int gameID;
        do{
            gameID = new Random().nextInt(999);
        }while(map.containsKey(gameID));
        return gameID;
    }


}
