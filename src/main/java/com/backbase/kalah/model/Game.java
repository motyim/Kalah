package com.backbase.kalah.model;

import com.backbase.kalah.exception.GameException;

import java.util.Arrays;

public class Game {

    private int [] houses ;
    private boolean playerOneTurn ;
    private boolean playerTwoTurn ;
    private final int PLAYER_ONE_HOUSE = 6;
    private final int PLAYER_TWO_HOUSE = 13;
    private final int TOTAL_HOUSES = 14;
    private String result;

    public Game(){
        houses = new int[TOTAL_HOUSES];
        //fill array with 6
        Arrays.fill(houses,6);
        // empty the main houses
        houses[PLAYER_ONE_HOUSE] = 0;
        houses[PLAYER_TWO_HOUSE] = 0;
        playerOneTurn = true;
    }


    public void play(int pitId) {

        if(isGameFinished())
            throw new GameException("Game Finished And Result is "+ result);

        //check turn
        inPlayerTurnRange(pitId);

        int i ;
        int seeds = houses[pitId];
        houses[pitId] = 0 ;

        for ( i = pitId+1; seeds > 0 ; i++) {

            if(isWillPutOnOppositeHouse(i)) continue;

            houses[i%TOTAL_HOUSES]++;
            seeds--;

            if(isLastStoneInOwnEmptyPit(pitId, i, seeds)){
                if(playerOneTurn){
                    houses[PLAYER_ONE_HOUSE]+= houses[i%TOTAL_HOUSES]+houses[12-(i%TOTAL_HOUSES)];
                }else{
                    houses[PLAYER_TWO_HOUSE]+= houses[i%TOTAL_HOUSES]+houses[12-(i%TOTAL_HOUSES)];
                }
                houses[i%TOTAL_HOUSES] = 0;
                houses[12-(i%TOTAL_HOUSES)]=0;
            }
        }

        //check game finish
        if(checkPlayerFinishGame(0,PLAYER_ONE_HOUSE) || checkPlayerFinishGame(7,PLAYER_TWO_HOUSE)){
            finishGame();
        }

        changeTurn(i-1);
    }

    private boolean isLastStoneInOwnEmptyPit(int pitId, int i, int seeds) {
        return seeds==0
                && i%TOTAL_HOUSES != PLAYER_ONE_HOUSE
                && i%TOTAL_HOUSES != PLAYER_TWO_HOUSE
                && inPlayerHouses(i%TOTAL_HOUSES , pitId)
                && houses[i%TOTAL_HOUSES] == 1
                && houses[12-(i%TOTAL_HOUSES)] != 0;
    }

    private boolean isWillPutOnOppositeHouse(int i) {
        return (i%TOTAL_HOUSES == PLAYER_ONE_HOUSE && playerTwoTurn) || (i%TOTAL_HOUSES == PLAYER_TWO_HOUSE && playerOneTurn);
    }

    private boolean isGameFinished() {
        return result != null;
    }

    private void finishGame() {
        for (int i = 0; i < PLAYER_ONE_HOUSE; i++) {
            houses[PLAYER_ONE_HOUSE] += houses[i];
            houses[i] = 0;
        }
        for (int i = 7; i < PLAYER_TWO_HOUSE; i++) {
            houses[PLAYER_TWO_HOUSE] += houses[i];
            houses[i] = 0;
        }
        if(houses[PLAYER_ONE_HOUSE] > houses[PLAYER_TWO_HOUSE]){
            result = "Player One Win";
        }else if(houses[PLAYER_TWO_HOUSE] > houses[PLAYER_ONE_HOUSE]){
            result = "Player Two Win";
        }else result = "Draw";
    }

    private boolean checkPlayerFinishGame(int start , int end) {
        for (int i = start; i < end; i++) {
            if(houses[i] != 0) return false ;
        }
        return true;
    }

    private boolean inPlayerHouses(int i, int pitId) {
        return (i < PLAYER_ONE_HOUSE && pitId < PLAYER_ONE_HOUSE)
                || (i > PLAYER_ONE_HOUSE && pitId > PLAYER_ONE_HOUSE) ;
    }

    private void inPlayerTurnRange(int pitId) {
        if(playerOneTurn  && pitId >= PLAYER_ONE_HOUSE)
            throw new RuntimeException("is player two turn");
        else if ( playerTwoTurn  &&  pitId < PLAYER_ONE_HOUSE )
            throw new RuntimeException("is player one turn");
    }

    private void changeTurn(int i) {
        if(!( isPlayerOneContinueTurn(i) || isPlayerTwoContinueTurn(i))) {
            playerOneTurn = !playerOneTurn;
            playerTwoTurn = !playerTwoTurn;
        }
    }

    private boolean isPlayerOneContinueTurn(int i) {
        return i == PLAYER_ONE_HOUSE && playerOneTurn;
    }

    private boolean isPlayerTwoContinueTurn(int i) {
        return i == PLAYER_TWO_HOUSE && playerTwoTurn;
    }

    public int[] getHouses() {
        return houses.clone();
    }
}
