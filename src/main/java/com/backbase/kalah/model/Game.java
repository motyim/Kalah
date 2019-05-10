package com.backbase.kalah.model;


import java.util.Arrays;

public class Game {

    private int [] houses ;

    public Game(){
        System.out.println("init game");
        houses = new int[14];
        //fill array with 6
        Arrays.fill(houses,6);
        // empty the main houses
        houses[6] = 0;
        houses[13] = 0;
    }


    public void play(int pitId) {

    }

    public int[] getHouses() {
        return houses.clone();
    }
}
