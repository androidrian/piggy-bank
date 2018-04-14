package com.example.filipe.piggybank.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Filipe on 24/04/2017.
 */

public class PiggyBank {
    public final String TAG = getClass().getName();

    private List<Coin> mListOfCoins = new ArrayList<>();
    private final int DEFAULT_VALUE = 0;

    public double totalWeightOfPiggyBank;
    public int totalNumberOfCoinsInPiggyBank;

    //this will go into a properties file or db
    final double WEIGHT_OF_COIN_DOIS_EUROS = 8.50;
    final double WEIGHT_OF_COIN_UM_EURO = 7.50;
    final double WEIGHT_OF_COIN_CINQUENTA_CENT = 7.80;
    final double WEIGHT_OF_COIN_VINTE_CENT = 5.70;
    final double WEIGHT_OF_COIN_DEZ_CENT = 4.10;
    final double WEIGHT_OF_COIN_CINCO_CENT = 3.90;
    final double WEIGHT_OF_COIN_DOIS_CENT = 3.05;
    final double WEIGHT_OF_COIN_UM_CENT = 2.30;
    //this will go into a properties file or db
    final String DOIS_EUROS_NAME = "doisEuros";
    final String UM_EURO_NAME = "umEuro";
    final String CINQUENTA_CENT_NAME = "cinquentaCent";
    final String VINTE_CENT_NAME = "vinteCent";
    final String DEZ_CENT_NAME = "dezCent";
    final String CINCO_CENT_NAME = "cincoCent";
    final String DOIS_CENT_NAME = "doisCent";
    final String UM_CENT_NAME = "umCent";
    //this will go into a properties file or db
    final double DOIS_EUROS_VALUE = 2;
    final double UM_EURO_VALUE = 1;
    final double CINQUENTA_CENT_VALUE = 0.50;
    final double VINTE_CENT_VALUE = 0.20;
    final double DEZ_CENT_VALUE = 0.10;
    final double CINCO_CENT_VALUE = 0.05;
    final double DOIS_CENT_VALUE = 0.02;
    final double UM_CENT_VALUE = 0.01;

    public final Coin COIN_DOIS_EUROS = new Coin(DOIS_EUROS_NAME,DOIS_EUROS_VALUE,WEIGHT_OF_COIN_DOIS_EUROS);
    public final Coin COIN_UM_EURO = new Coin(UM_EURO_NAME,UM_EURO_VALUE,WEIGHT_OF_COIN_UM_EURO);
    public final Coin COIN_CINQUENTA_CENT = new Coin(CINQUENTA_CENT_NAME,CINQUENTA_CENT_VALUE,WEIGHT_OF_COIN_CINQUENTA_CENT);
    public final Coin COIN_VINTE_CENT = new Coin(VINTE_CENT_NAME,VINTE_CENT_VALUE,WEIGHT_OF_COIN_VINTE_CENT);
    public final Coin COIN_DEZ_CENT = new Coin(DEZ_CENT_NAME,DEZ_CENT_VALUE,WEIGHT_OF_COIN_DEZ_CENT);
    public final Coin COIN_CINCO_CENT = new Coin(CINCO_CENT_NAME,CINCO_CENT_VALUE,WEIGHT_OF_COIN_CINCO_CENT);
    public final Coin COIN_DOIS_CENT = new Coin(DOIS_CENT_NAME,DOIS_CENT_VALUE,WEIGHT_OF_COIN_DOIS_CENT);
    public final Coin COIN_UM_CENT = new Coin(UM_CENT_NAME,UM_CENT_VALUE,WEIGHT_OF_COIN_UM_CENT);

    private int[] qtyOfEachCoin = new int[8];
    private Map<Coin,Integer> mapOfCoinsQuantity = new HashMap<>();


    /**
     * Default constructor
     */
    public PiggyBank(){

    }

    public void initPiggyBank(){
        setListOfCoins();
        setMapOfCoinsQuantity();
        this.totalWeightOfPiggyBank = 0;
        this.totalNumberOfCoinsInPiggyBank = 0;
    }

    private void setListOfCoins()
    {
        mListOfCoins.add(COIN_DOIS_EUROS);
        mListOfCoins.add(COIN_UM_EURO);
        mListOfCoins.add(COIN_CINQUENTA_CENT);
        mListOfCoins.add(COIN_VINTE_CENT);
        mListOfCoins.add(COIN_DEZ_CENT);
        mListOfCoins.add(COIN_CINCO_CENT);
        mListOfCoins.add(COIN_DOIS_CENT);
        mListOfCoins.add(COIN_UM_CENT);
    }
    private void setWeightsOfCoins()
    {

    }

    private void setMapOfCoinsQuantity(){
        for(Coin coin : mListOfCoins){
            mapOfCoinsQuantity.put(coin,DEFAULT_VALUE);
        }
    }

    /**
     * Iterates trough an array with the qty of each coin in Piggybank and sums it up into a total number var
     * ex: [0,1,2,3,4,5,6,7] each position corresponds to [2,1,0.50,0.20,0.10,0.05,0.02,0.01]
     * @return the total number of coins in piggybank
     */
    public int getTotalNumberOfCoins(){
        int totalNumberOfCoins = 0;

        for(Integer qty : qtyOfEachCoin){
            totalNumberOfCoins =+ qty;
        }

        return totalNumberOfCoins;
    }

    public double getTotalWeightOfPiggyBank(){
        return totalWeightOfPiggyBank;
    }

    public int getTotalNumberOfCoinsInPiggyBank(){
        return totalNumberOfCoinsInPiggyBank;
    }


    /**
     * The list of coin types
     * @return
     */
    public List<Coin> getListOfCoins()
    {
        return mListOfCoins;
    }
}
