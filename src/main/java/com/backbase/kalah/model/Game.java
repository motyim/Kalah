package com.backbase.kalah.model;

import java.util.Arrays;

public class Game {

    private int [] houses ;
    private boolean playerOneTurn = true ;
    private boolean playerTwoTurn = false ;
    private final static int PLAYER_ONE_HOUSE = 6;
    private final static int PLAYER_TWO_HOUSE = 13;
    private String result = null;

    public Game(){
        houses = new int[14];
        //fill array with 6
        Arrays.fill(houses,6);
        // empty the main houses
        houses[6] = 0;
        houses[13] = 0;
    }


    public void play(int pitId) {
        if(result != null)
            throw new RuntimeException("Game Finsih And Result is "+ result);
        int i ;
        //check turn
        inPlayerTurnRange(pitId);
        int seeds = houses[pitId];
        houses[pitId] = 0 ;
        for ( i = pitId+1; seeds > 0 ; i++) {
            //not put on opposite hole
            if(i%14 == PLAYER_ONE_HOUSE && playerTwoTurn) continue;
            if(i%14 == PLAYER_TWO_HOUSE && playerOneTurn) continue;

            houses[i%14]++;
            seeds--;
            //last move & last index i put in & opposite have value
            if(seeds==0
                    && i%14 != PLAYER_ONE_HOUSE
                    && i%14 != PLAYER_TWO_HOUSE
                    && inPlayerHouses(i%14 , pitId)
                    && houses[i%14] == 1
                    && houses[12-(i%14)] != 0){
                if(playerOneTurn){
                    houses[PLAYER_ONE_HOUSE]+= houses[i%14]+houses[12-(i%14)];
                }else{
                    houses[PLAYER_TWO_HOUSE]+= houses[i%14]+houses[12-(i%14)];
                }
                houses[i%14] = 0;
                houses[12-(i%14)]=0;
            }
        }

        //check game finish
        if(checkPlayerFinishGame(0,PLAYER_ONE_HOUSE) || checkPlayerFinishGame(7,PLAYER_TWO_HOUSE)){
            finishGame();
        }

        changeTurn(i-1);
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
        if(playerOneTurn  && pitId > 5)
            throw new RuntimeException("is player two turn");
        else if ( playerTwoTurn  && ( pitId >12 || pitId <7 ))
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
