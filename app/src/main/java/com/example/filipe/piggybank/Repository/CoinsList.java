package com.example.filipe.piggybank.Repository;

import com.example.filipe.piggybank.Model.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinsList {

    public final String TAG = getClass().getName();
    private Coin coin;
    private ArrayList<Coin> listOfCoins;

    /**
     * Default Constructor
     */
    public CoinsList(){

    }

    public List<Coin> getListOfCoins(){
        return listOfCoins;
    }

    public void addCoinToList(Coin coin){
        listOfCoins.add(coin);
    }


}
