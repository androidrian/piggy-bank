package com.example.filipe.piggybank.Model;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 23/02/2017.
 */

public class CoinOperations {

    private String DEFAULT_VALUE = "0";

    DecimalFormat df = new DecimalFormat("#.00");

    public CoinOperations()
    {

    }

    //refactor this method
    public double getTotalSumOfCoins(List<EditText> list, List<Double> l_values)
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


    private String getTypeOfButtonName(View v)
    {
        String nameTypeOfButton, idAsString;

        idAsString = v.getResources().getResourceEntryName(v.getId());
        nameTypeOfButton = idAsString.substring(0,3);//"inc" or "dec" of increment and decrement

        return nameTypeOfButton;

    }










}