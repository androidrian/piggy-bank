package com.example.filipe.piggybank.Model;

/**
 * Created by Filipe on 23/04/2017.
 */

public class Coin {

    private String mCoinName;
    private double mCoinValue;



    public Coin()
    {

    }

    public Coin(String coinName, double coinValue)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
    }



    public String getCoinName() {
        return mCoinName;
    }

    public double getCoinValue() {
        return mCoinValue;
    }

}

