package com.backbase.kalah.model;

import com.backbase.kalah.exception.GameException;

import static com.backbase.kalah.model.Game.*;

public class GameEngine {

    private Game game;
    private boolean playerOneTurn ;
    private boolean playerTwoTurn ;
    private String result;

    public GameEngine(){
        game = new Game();
        playerOneTurn = true;
    }


    public void play(int pitId) {

        if(isGameFinished())
            throw new GameException("GameEngine Finished And Result is "+ result);

        inPlayerTurnRange(pitId);

        int i ;
        int seeds = game.getHouseValue(pitId);
        game.setHouseValue(pitId,0);


        for ( i = pitId+1; seeds > 0 ; i=(i+1)%TOTAL_HOUSES) {

            if(isWillPutOnOppositeHouse(i)) continue;
            game.addToHouseValue(i%TOTAL_HOUSES,1);
            seeds--;

            if(isLastStoneInOwnEmptyPit(pitId, i, seeds)){
                if(playerOneTurn){
                    game.addToMainHouseTwoOppositePit(PLAYER_ONE_HOUSE,i);
                }else{
                    game.addToMainHouseTwoOppositePit(PLAYER_TWO_HOUSE,i);
                }
                game.setHouseValue(i,0);
                game.setHouseValue(12-i,0);
            }
        }

        determineEndOfGame();

        changeTurn(i-1);
    }

    private void determineEndOfGame() {
        if(checkPlayerFinishGame(0, PLAYER_ONE_HOUSE) || checkPlayerFinishGame(7, PLAYER_TWO_HOUSE)){
            finishGame();
        }
    }

    private boolean isLastStoneInOwnEmptyPit(int pitId, int i, int seeds) {
        return seeds==0
                && i != PLAYER_ONE_HOUSE
                && i != PLAYER_TWO_HOUSE
                && inPlayerHouses(i , pitId)
                && game.getHouseValue(i) == 1
                && game.getHouseValue(12-i) != 0;
    }

    private boolean isWillPutOnOppositeHouse(int i) {
        return (i%TOTAL_HOUSES == PLAYER_ONE_HOUSE && playerTwoTurn) || (i%TOTAL_HOUSES == PLAYER_TWO_HOUSE && playerOneTurn);
    }

    private boolean isGameFinished() {
        return result != null;
    }

    private void finishGame() {
        for (int i = 0; i < PLAYER_ONE_HOUSE; i++) {
            game.addToHouseValue(PLAYER_ONE_HOUSE,game.getHouseValue(i));
            game.setHouseValue(i,0);
        }
        for (int i = 7; i < PLAYER_TWO_HOUSE; i++) {
            game.addToHouseValue(PLAYER_TWO_HOUSE,game.getHouseValue(i));
            game.setHouseValue(i,0);
        }
        if(game.getHouseValue(PLAYER_ONE_HOUSE) > game.getHouseValue(PLAYER_TWO_HOUSE)){
            result = "Player One Win";
        }else if(game.getHouseValue(PLAYER_TWO_HOUSE) > game.getHouseValue(PLAYER_ONE_HOUSE)){
            result = "Player Two Win";
        }else result = "Draw";
    }

    private boolean checkPlayerFinishGame(int start , int end) {
        for (int i = start; i < end; i++) {
            if(game.getHouseValue(i) != 0) return false ;
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
        return game.getHouses();
    }
}
