package com.example.filipe.piggybank.Model;

/**
 * Created by Filipe on 23/04/2017.
 */

public class Coin {

    public final String TAG = getClass().getName();
    private String mCoinName;
    private double mCoinValue;
    private double mCoinWeight;
    private int mquantity;


    /**
     * Default constructor
     */
    public Coin()
    {

    }

    /**
     * Coin constructor
     * @param coinName
     * @param coinValue
     */
    public Coin(String coinName, double coinValue)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
    }

    /**
     * Coin constructor
     * @param coinName
     * @param coinValue
     * @param coinWeight
     */
    public Coin(String coinName, double coinValue, double coinWeight)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
        this.mCoinWeight = coinWeight;
    }

    public Coin(String coinName, double coinValue, double coinWeight, int quantity)
    {
        this.mCoinName = coinName;
        this.mCoinValue = coinValue;
        this.mCoinWeight = coinWeight;
        this.mquantity = quantity;
    }



    public String getCoinName() {
        return mCoinName;
    }

    public double getCoinValue() {
        return mCoinValue;
    }

    public double getCoinWeight(){
        return mCoinWeight;
    }

    public void setCoinName(String name){
        mCoinName = name;
    }

    public void setCoinValue(Double value){
        mCoinValue = value;
    }

    public void setCoinWeight(Double weight){
        mCoinWeight = weight;
    }

    public void setCoinQuantity(int quantity){
        mquantity = quantity;
    }

    public int returnCoinQuantity(){
        return this.mquantity;
    }


}

