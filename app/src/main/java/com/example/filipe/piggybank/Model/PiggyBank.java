package com.example.filipe.piggybank.Model;

import android.support.v4.util.SimpleArrayMap;

import java.util.List;

/**
 * Created by Filipe on 24/04/2017.
 */

public class PiggyBank {

    private Coin coin;
    private List<Coin> mListOfCoins;
    private double total;

    public PiggyBank(List<Coin> listOfCoins)
    {
        this.mListOfCoins = listOfCoins;
    }

    private void setListOfCoins()
    {
        coin = new Coin("DoisE",2);
        mListOfCoins.add(coin);
        coin = new Coin("UmE",1);
        mListOfCoins.add(coin);
        coin = new Coin("CinqC",0.50);
        mListOfCoins.add(coin);
        coin = new Coin("VinteC",0.20);
        mListOfCoins.add(coin);
        coin = new Coin("DezC",0.10);
        mListOfCoins.add(coin);
        coin = new Coin("CincoC",0.05);
        mListOfCoins.add(coin);
        coin = new Coin("DoisC",0.02);
        mListOfCoins.add(coin);
        coin = new Coin("UmC",0.01);
        mListOfCoins.add(coin);




    }
}
