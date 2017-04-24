package com.example.filipe.piggybank.Services;

/**
 * Created by Filipe on 18/04/2017.
 */

public class PredictionFormulas {

    public PredictionFormulas()
    {

    }


    public double getTotalPreditionOnTargetDay(double actualTotal, int presentDay, int targetDay)
    {
        double predictionTotal;
        int daysToTargetDay = targetDay - presentDay;//12

        predictionTotal = (daysToTargetDay * actualTotal)/presentDay;

        return predictionTotal;

    }
}
