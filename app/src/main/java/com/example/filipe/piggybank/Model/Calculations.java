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

public class Calculations {

    private String DEFAULT_VALUE = "0";

    DecimalFormat df = new DecimalFormat("#.00");

    public Calculations()
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

    /**
     *
     * @param view
     * @param coinInputField
     * @return
     */
    public double getDifferenceOfFinalValueInGivenCoinInputField(View view, EditText coinInputField)
    {

        String nameTypeOfButton,numberOfCoinsString;
        nameTypeOfButton = getTypeOfButtonName(view);//incrementButton or decrementButton
        nameTypeOfButton = nameTypeOfButton.substring(0,3);//"inc" or "dec"
        numberOfCoinsString = coinInputField.getText().toString();

        //index--;
        int totalNumberOfCoinsBefore, totalNumberOfCoinsAfter;
        int diff;
        if(numberOfCoinsString.equalsIgnoreCase(""))
        {
            totalNumberOfCoinsBefore = 0;
        }
        else
        {
            totalNumberOfCoinsBefore = Integer.valueOf(numberOfCoinsString);
        }

        if(nameTypeOfButton.equalsIgnoreCase("inc")) {
            addCoin(coinInputField);
        }
        if(nameTypeOfButton.equalsIgnoreCase("dec")) {
           takeCoin(coinInputField);
        }

        totalNumberOfCoinsAfter = Integer.valueOf(numberOfCoinsString);

        diff = totalNumberOfCoinsAfter - totalNumberOfCoinsBefore;


        return diff;
    }


    private String getTypeOfButtonName(View v)
    {
        String nameTypeOfButton, idAsString;

        idAsString = v.getResources().getResourceEntryName(v.getId());
        nameTypeOfButton = idAsString.substring(0,3);//"inc" or "dec" of increment and decrement

        return nameTypeOfButton;

    }



    public double updateTotalValue(double diff, List<Double> l_values, int index, EditText totalValue)
    {
        double diffOfValue = diff * l_values.get(index-1);
        double totalValueBefore = Double.valueOf(totalValue.getText().toString());
        double totalValueUpdate = totalValueBefore + diffOfValue;

        return totalValueUpdate;
    }


    private void addCoin(EditText coinInputField){
        double total;
        String numberOfCoinsText = coinInputField.getText().toString();

        //se nao tiver nada para nao dar null tem que ficar a zero
        //ou entao fazer catch do NullPointerException
        if(numberOfCoinsText.equalsIgnoreCase(""))
        {
            //total = 0;
            coinInputField.setText(DEFAULT_VALUE);
            total = Integer.valueOf(numberOfCoinsText);
        }
        else
        {
            total = Integer.valueOf(numberOfCoinsText);
            total++;
        }

        coinInputField.setText(String.valueOf((total)));


    }

    private void takeCoin(EditText coinInputField){
        double total;
        String numberOfCoins;

        numberOfCoins = coinInputField.getText().toString();

        total = Integer.valueOf(numberOfCoins);
        total--;

        if(total < 0)
        {
            total = 0;
        }
        coinInputField.setText(String.valueOf((total)));
    }









}
