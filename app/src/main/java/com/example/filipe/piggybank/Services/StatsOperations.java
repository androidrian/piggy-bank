package com.example.filipe.piggybank.Services;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 21/04/2017.
 */

public class StatsOperations {

    DecimalFormat df = new DecimalFormat("#.00");


    public StatsOperations()
    {

    }

    /**
     * Calculate the relative frequency = (Absolute Frequency/Total)
     * of the number of coins that exists in the piggy bank
     * @param l_absoluteValues the list the absolute frequency values of each coin
     * @return the list with relative frequency of each coin
     */
    public ArrayList<Double> getListWithRelativeFrequencyOfCoins(List<Integer> l_absoluteValues)
    {
        ArrayList<Double> l_rFrequencyOfCoins = new ArrayList<>();
        int totalCoinsInPiggyBank =
                getTotalNumberOfCoinsInBank(l_absoluteValues);

        for(Integer absoluteValue : l_absoluteValues) {

            double rFrequencyOfCoin;
            if (totalCoinsInPiggyBank != 0){//can't divide by 0
                double value =
                        (double)absoluteValue/totalCoinsInPiggyBank;
                rFrequencyOfCoin = Double.valueOf(df.format(value));
                l_rFrequencyOfCoins.add(rFrequencyOfCoin);
            }else{
                rFrequencyOfCoin = 0;
                l_rFrequencyOfCoins.add(rFrequencyOfCoin);
            }
        }
        return l_rFrequencyOfCoins;
    }



    /**
     * Iterates trough a list with the total number of each coin
     * and stores its sum in a variable to return the total number of coins in the piggy bank
     * @param listTotalNumberOfEachCoin the list that has the total number of coins of each coin
     * @return the sum of all coins
     */
    private int getTotalNumberOfCoinsInBank(List<Integer> listTotalNumberOfEachCoin)
    {
        int totalNumberOfAllCoins = 0;
        for(Integer totalNumberOfCoin : listTotalNumberOfEachCoin)
        {
            totalNumberOfAllCoins += totalNumberOfCoin;
        }
        return totalNumberOfAllCoins;
    }

    /**
     * Sets a list with percentage values using the list with the frequency values and
     * multiplying each value for 100.
     * @param l_frequencyValues the list with the frequency values
     * @return the list with the values in percentage (value * 100)
     */
    public ArrayList<Double> setListWithPercentageOfCoins(List<Double> l_frequencyValues)
    {
        ArrayList<Double> l_coinPercentage = new ArrayList<>();

        for(Double frValue: l_frequencyValues)
        {
            double valueInPercentage = Double.valueOf(df.format(frValue * 100));
            l_coinPercentage.add(valueInPercentage);
        }
        return l_coinPercentage;
    }
}
