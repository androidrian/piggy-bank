package com.example.filipe.piggybank.Model;

import android.support.v4.util.SimpleArrayMap;

import java.util.List;

/**
 * Created by Filipe on 24/04/2017.
 */

public class PiggyBank {

    public final String TAG = getClass().getName();
    private Coin coin;
    private List<Coin> mListOfCoins;
    private Coin COIN_DOIS_EUROS,COIN_UM_EURO,COIN_CINQUENTA_CENT,COIN_VINTE_CENT,
            COIN_DEZ_CENT,COIN_CINCO_CENT,COIN_DOIS_CENT,COIN_UM_CENT;

    final double WEIGHT_OF_COIN_DOIS_EUROS = 8.50;
    final double WEIGHT_OF_COIN_UM_EURO = 7.50;
    final double WEIGHT_OF_COIN_CINQUENTA_CENT = 8.50;
    final double WEIGHT_OF_COIN_VINTE_CENT = 8.50;
    final double WEIGHT_OF_COIN_DEZ_CENT = 8.50;
    final double WEIGHT_OF_COIN_CINCO_CENT = 8.50;
    final double WEIGHT_OF_COIN_DOIS_CENT = 8.50;
    final double WEIGHT_OF_COIN_UM_CENT = 8.50;

    public PiggyBank(){
        this.COIN_DOIS_EUROS = new Coin("doisEuros",2,WEIGHT_OF_COIN_DOIS_EUROS);
        this.COIN_UM_EURO = new Coin("umEuro",1,WEIGHT_OF_COIN_UM_EURO);
        this.COIN_CINQUENTA_CENT = new Coin("cinquentaCent",0.50,WEIGHT_OF_COIN_CINQUENTA_CENT);
        this.COIN_VINTE_CENT = new Coin("vinteCent",0.20,WEIGHT_OF_COIN_VINTE_CENT);
        this.COIN_DEZ_CENT = new Coin("dezCent",0.10,WEIGHT_OF_COIN_DEZ_CENT);
        this.COIN_CINCO_CENT = new Coin("cincoCent",0.05,WEIGHT_OF_COIN_CINCO_CENT);
        this.COIN_DOIS_CENT = new Coin("doisCent",0.02,WEIGHT_OF_COIN_DOIS_CENT);
        this.COIN_UM_CENT = new Coin("umCent",0.01,WEIGHT_OF_COIN_UM_CENT);
        setListOfCoins();
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


    /**
     * The list of coin types
     * @return
     */
    public List<Coin> getListOfCoins()
    {
        return mListOfCoins;
    }
}
