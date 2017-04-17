package com.example.filipe.piggybank;

import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 23/02/2017.
 */

public class Calcs {

    private String DEFAULT_VALUE = "0";

    DecimalFormat df = new DecimalFormat("#.00");

    public Calcs()
    {

    }

    //refactor this method
    public double getTotalSumOfCoins(ArrayList<EditText> list, List<Double> l_values)
    {
        double totalSumOfCoins = 0;
        int size = list.size();
        double value;

        for(int i =0; i < size; i++)
        {
            //refactor this method
            value = getTotalSumOfCoinValue(list.get(i),i,l_values);
            totalSumOfCoins += value;
        }

        return totalSumOfCoins;
    }

    //refactor this method
    public double getTotalSumOfCoinValue(EditText editText, int index, List<Double> l_coinValues)
    {
        double total, numberOfCoins, valueOfCoin;
        String numberOfCoinsText;

        numberOfCoinsText = editText.getText().toString();
        if(!numberOfCoinsText.equalsIgnoreCase(""))
        {
            numberOfCoins = Integer.valueOf(numberOfCoinsText);
            valueOfCoin = l_coinValues.get(index);
            total = numberOfCoins * valueOfCoin;
        }else{
            total = 0; //pq supostamente nao tem valor nenhum lá
            editText.setText(DEFAULT_VALUE);//isto nao devia estar aqui mas para ja fica
        }
        return total;
    }

    public double getDifferenceOfTotalValueBeforeAfter(View v, EditText editText)
    {

        String idAsString,nameTypeOfButton,numberOfCoins;
        int totalNumberOfCoinsBefore;

        idAsString = v.getResources().getResourceEntryName(v.getId());
        nameTypeOfButton = idAsString.substring(0,3);//"inc" or "dec" of increment and decrement
        numberOfCoins = editText.getText().toString();

        //index--;

        if(numberOfCoins.equalsIgnoreCase(""))
        {
            totalNumberOfCoinsBefore = 0;
        }
        else
        {
            totalNumberOfCoinsBefore = Integer.valueOf(numberOfCoins);
        }

        if(nameTypeOfButton.equalsIgnoreCase("inc")) {
            editText.setText(String.valueOf((int) addCoin(editText)));
        }
        if(nameTypeOfButton.equalsIgnoreCase("dec"))
        {
            editText.setText(String.valueOf((int) takeCoin(editText)));
        }

        int totalNumberOfCoinsAfter = Integer.valueOf(numberOfCoins);

        int diff = totalNumberOfCoinsAfter - totalNumberOfCoinsBefore;


        return diff;
    }

    public double updateTotalValue(double diff, List<Double> l_values, int index, EditText totalValue)
    {
        double diffOfValue = diff * l_values.get(index-1);
        double totalValueBefore = Double.valueOf(totalValue.getText().toString());
        double totalValueUpdate = totalValueBefore + diffOfValue;

        return totalValueUpdate;
    }

    //this method is buggy
    //este metodo talvez devesse ser void apenas para alterar o valor do total e depois arranjar outro metodo para devolver
    public double addCoin(EditText editText){
        double total;
        String numberOfCoinsText = editText.getText().toString();

        //se nao tiver nada para nao dar null tem que ficar a zero
        //ou entao fazer catch do NullPointerException
        if(numberOfCoinsText.equalsIgnoreCase(""))
        {
            //total = 0;
            editText.setText(DEFAULT_VALUE);
            total = Integer.valueOf(numberOfCoinsText);
        }
        else
        {
            total = Integer.valueOf(numberOfCoinsText);
            total++;
        }

        return total;
    }
    //este método tb deveria talvez ser void...convém depois ver o que se passa
    public double takeCoin(EditText editText){
        double total;
        String numberOfCoins;

        numberOfCoins = editText.getText().toString();

        total = Integer.valueOf(numberOfCoins);
        total--;

        if(total < 0)
        {
            total = 0;
        }
        return total;
    }


    /**
     * Iterates trough a list with the total number of each coin
     * and stores its sum in a variable to return the total number of coins in the piggy bank
     * @param l_totalNumberOfEachCoin the list that has the total number of coins of each coin
     * @return the sum of all coins
     */
    private int getTotalNumberOfCoinsInPiggyBank(List<Integer> l_totalNumberOfEachCoin)
    {
        int totalNumberOfAllCoins = 0;
        for(Integer totalNumberOfCoin : l_totalNumberOfEachCoin)
        {
            totalNumberOfAllCoins += totalNumberOfCoin;
        }
        return totalNumberOfAllCoins;
    }

    /**
     * Calculate the relative frequency (Absolute Frequency/Total) of the number of coins that exists in the piggy bank
     * @param l_absoluteValues the list the absolute frequency values of each coin
     * @return the list with relative frequency of each coin
     */
    public ArrayList<Double> getListWithRelativeFrequencyOfCoins(List<Integer> l_absoluteValues)
    {
        ArrayList<Double> l_relativeFrOfCoins = new ArrayList<>();
        int totalCoinsInPiggyBank = getTotalNumberOfCoinsInPiggyBank(l_absoluteValues);
       for(Integer absoluteFrOfCoin : l_absoluteValues) {
           double relativeFrOfCoin;
           if (totalCoinsInPiggyBank != 0)//can't divide by 0
           {
               relativeFrOfCoin = Double.valueOf(df.format( absoluteFrOfCoin/totalCoinsInPiggyBank) );
               l_relativeFrOfCoins.add(relativeFrOfCoin);
           }else
           {
               relativeFrOfCoin = 0;
               l_relativeFrOfCoins.add(relativeFrOfCoin);
           }
        }
        return l_relativeFrOfCoins;
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
