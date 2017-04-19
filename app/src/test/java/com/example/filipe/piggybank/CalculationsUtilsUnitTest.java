package com.example.filipe.piggybank;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Filipe on 18/04/2017.
 */

public class CalculationsUtilsUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    private void getTotalNumberOfCoinsInPiggyBank(List<Integer> l_totalNumberOfEachCoin)
    {
        int totalNumberOfAllCoins = 0;
        for(Integer totalNumberOfCoin : l_totalNumberOfEachCoin)
        {
            totalNumberOfAllCoins += totalNumberOfCoin;
        }
        //assertEquals(esperado,actual)

    }
}
