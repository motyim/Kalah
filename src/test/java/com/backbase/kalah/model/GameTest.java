package com.backbase.kalah.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testSimpleMove(){
        GameEngine game = new GameEngine();
        game.play(0);
        int [] result = {0,7,7,7,7,7,1,6,6,6,6,6,6,0};
        assertArrayEquals(result,game.getHouses());
    }

    @Test
    public void testTwoSimpleMoveForTwoPlayers(){
        GameEngine game = new GameEngine();
        game.play(1);
        game.play(8);
        int [] result = {7,0,7,7,7,7,1,7,0,7,7,7,7,1};
        assertArrayEquals(result,game.getHouses());
    }


    @Test
    public void testTwoSimpleMoveForOnePlayer(){
        GameEngine game = new GameEngine();
        game.play(0);
        game.play(1);
        int [] result = {0,0,8,8,8,8,2,7,7,6,6,6,6,0};
        assertArrayEquals(result,game.getHouses());
    }

    @Test(expected = RuntimeException.class)
    public void testTwoMoveForOnePlayerException(){
        GameEngine game = new GameEngine();
        game.play(1);
        game.play(2);
    }

    @Test
    public void testNotToPutInOppositeMainHouse(){
        GameEngine game = new GameEngine();
        game.play(5);
        game.play(11);
        game.play(4);
        game.play(11);
        game.play(5);
        game.play(3);
        game.play(12);
        int [] result = {8,8,8,1,2,2,4,10,9,9,9,0,0,2};
        assertArrayEquals(result,game.getHouses());
    }

    @Test
    public void testCaptionOppositeHouse(){
        GameEngine game = new GameEngine();
        game.play(3);
        game.play(7);
        game.play(4);
        game.play(8);
        game.play(3);
        game.play(7);
        int [] result = {8,7,7,0,0,8,2,0,0,10,9,9,8,4};
        System.out.println(Arrays.toString(game.getHouses()));
        assertArrayEquals(result,game.getHouses());
    }

    @Test(expected = RuntimeException.class)
    public void testFullGame(){
        GameEngine game = new GameEngine();
        int [] result;
        game.play(0);game.play(5);
        game.play(7);
        game.play(4);
        game.play(10);
        game.play(5);
        game.play(7);
        game.play(3);
        game.play(8);
        game.play(5);
        game.play(9);
        result =new int[] {4,10,10,2,0,1,6,3,0,0,3,13,11,9};
        assertArrayEquals(result,game.getHouses());

        game.play(5);game.play(3);
        game.play(11);
        result =new int[] {5,0,11,1,2,1,11,1,1,1,4,0,12,22};
        assertArrayEquals(result,game.getHouses());

        game.play(5);game.play(4);game.play(5);game.play(3);
        result =new int[] {5,0,11,0,0,0,16,1,0,1,4,0,12,22};
        assertArrayEquals(result,game.getHouses());

        game.play(7);
        game.play(2);
        game.play(12);
        game.play(5);
        result =new int[] {0,1,1,2,2,0,18,3,3,3,6,2,0,31};
        assertArrayEquals(result,game.getHouses());

        game.play(11);game.play(12);game.play(7);
        game.play(4);game.play(5);game.play(3);
        game.play(9);game.play(12);game.play(11);
        game.play(5);game.play(4);
        game.play(12);game.play(10);
        game.play(5);game.play(4);
        game.play(12);game.play(8);
        game.play(5);game.play(4);
        game.play(11);game.play(12);game.play(10);
        game.play(4);
        game.play(9);
        game.play(5);
        result =new int[] {0,0,0,0,0,0,24,0,0,0,0,0,0,48};
        assertArrayEquals(result,game.getHouses());
    }




}