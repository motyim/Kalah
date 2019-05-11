package com.backbase.kalah.model;

import java.util.Arrays;

public class Game {

    private int [] houses ;
    public static final int PLAYER_ONE_HOUSE = 6;
    public static final int PLAYER_TWO_HOUSE = 13;
    public static final int TOTAL_HOUSES = 14;

    public Game(){
        houses = new int[TOTAL_HOUSES];
        Arrays.fill(houses,6);
        houses[PLAYER_ONE_HOUSE] = 0;
        houses[PLAYER_TWO_HOUSE] = 0;
    }

    public int getHouseValue(int index){
        return houses[index];
    }

    public void setHouseValue(int index,int value){
        houses[index] = value;
    }

    public void addToHouseValue(int index,int value){
        houses[index] += value;
    }

    public void addToMainHouseTwoOppositePit(int mainHouse, int houseIndex){
        houses[mainHouse]+= houses[houseIndex]+houses[12-houseIndex];
    }

    public int[] getHouses() {
        return houses.clone();
    }
}
