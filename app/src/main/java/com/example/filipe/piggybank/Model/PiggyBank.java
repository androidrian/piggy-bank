package com.example.filipe.piggybank.Model;

import android.support.v4.util.SimpleArrayMap;

import java.util.List;

/**
 * Created by Filipe on 24/04/2017.
 */

public class PiggyBank {

    private Coin coin;
    private List<Coin> mListOfCoins;


    public PiggyBank(List<Coin> listOfCoins)
    {
        this.mListOfCoins = listOfCoins;
    }

    private void setListOfCoins()
    {
        coin = new Coin("coin_2",2);
        mListOfCoins.add(coin);
        coin = new Coin("coin_1",1);
        mListOfCoins.add(coin);
        coin = new Coin("coin_050",0.50);
        mListOfCoins.add(coin);
        coin = new Coin("coin_020",0.20);
        mListOfCoins.add(coin);
        coin = new Coin("coin_010",0.10);
        mListOfCoins.add(coin);
        coin = new Coin("coin_005",0.05);
        mListOfCoins.add(coin);
        coin = new Coin("coin_002",0.02);
        mListOfCoins.add(coin);
        coin = new Coin("coin_001",0.01);
        mListOfCoins.add(coin);
    }

    /**
     * The list of coin types
     * @return
     */
    public List<Coin> getListOfTypeOfCoins()
    {
        return mListOfCoins;
    }
}
