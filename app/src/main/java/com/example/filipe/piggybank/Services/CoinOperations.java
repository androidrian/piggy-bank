package com.example.filipe.piggybank.Services;

import android.app.Activity;
import android.database.DataSetObservable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filipe.piggybank.DB.DatabaseHelper;
import com.example.filipe.piggybank.Views.MainActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 23/02/2017.
 */

public class CoinOperations {

    private String TAG = "CoinOperations";
    private String DEFAULT_VALUE = "0";

    DecimalFormat df = new DecimalFormat("#.00");

    public CoinOperations()
    {

    }

    //TODO refactor this method
    public double getTotalSumOfCoins(List<EditText> list, List<Double> l_values)
    {
        double totalSumOfCoins = 0;
        int size = list.size();
        double value;

        for(int i =0; i < size; i++)
        {
            //TODO refactor this method
            value = getTotalSumOfCoinValue(list.get(i),i,l_values);
            totalSumOfCoins += value;
        }

        return totalSumOfCoins;
    }

    //TODO refactor this method
    /**
     *
     * @param editText the EditText of the 
     * @param index
     * @param l_coinValues
     * @return
     */
    private double getTotalSumOfCoinValue(EditText editText, int index, List<Double> l_coinValues)
    {
        double total, numberOfCoins, valueOfCoin;
        String numberOfCoinsAsString;

        numberOfCoinsAsString = editText.getText().toString();
        if(!numberOfCoinsAsString.equalsIgnoreCase(""))
        {
            numberOfCoins = Integer.valueOf(numberOfCoinsAsString);
            valueOfCoin = l_coinValues.get(index);
            total = numberOfCoins * valueOfCoin;
        }else{
            //TODO tentar replicar cenário para verificar se o erro ainda se mantém (CoinOperations)
            total = 0; //pq supostamente nao tem valor nenhum lá
            editText.setText(DEFAULT_VALUE);//isto nao devia estar aqui mas para ja fica
        }
        return total;
    }

    public double takeCoinFromEditText(EditText editText){
        double total;
        String numberOfCoins = editText.getText().toString();
        total = Integer.valueOf(numberOfCoins);
        total--;

        if(total < 0 )
        {
            total = 0;
        }
        return total;
    }

    public double addCoinToEditText(EditText numberOfCoinsEditText){
        double total;
        String numberOfCoinsText = numberOfCoinsEditText.getText().toString();
        System.out.println("NUMBER OF COINS TEXT:" + numberOfCoinsText);

        //TODO verificar aqui (CoinOperations) esta situação : se nao tiver nada para nao dar null tem que ficar a zero ou entao fazer catch do NullPointerException
        if(numberOfCoinsText.equalsIgnoreCase(""))
        {
            numberOfCoinsEditText.setText(DEFAULT_VALUE);//total = 0
            total = Integer.valueOf(numberOfCoinsText);
        }
        else
        {
            total = Integer.valueOf(numberOfCoinsText);
            total++;
        }

        return total;
    }


}
