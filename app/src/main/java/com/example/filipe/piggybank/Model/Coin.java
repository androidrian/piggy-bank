package com.example.filipe.piggybank.Model;

/**
 * Created by Filipe on 23/04/2017.
 */

public class Coin {

    public final String TAG = getClass().getName();
    private String mCoinName;
    private double mCoinValue;
    private double mCoinWeight;



    public Coin()
    {

    }

    public Coin(String coinName, double coinValue)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
    }

    public Coin(String coinName, double coinValue, double coinWeight)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
        this.mCoinWeight = coinWeight;
    }



    public String getCoinName() {
        return mCoinName;
    }

    public double getCoinValue() {
        return mCoinValue;
    }

    public void setCoinName(String name){
        mCoinName = name;
    }

    public void setCoinValue(Double value){
        mCoinValue = value;
    }
}

