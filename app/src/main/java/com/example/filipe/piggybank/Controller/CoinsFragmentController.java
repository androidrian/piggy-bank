package com.example.filipe.piggybank.Controller;

import com.example.filipe.piggybank.Views.CoinsFragment;
import com.example.filipe.piggybank.Services.CoinOperations;

/**
 * Created by Filipe on 22/04/2017.
 */

public class CoinsFragmentController {

    private CoinsFragment mCoinsFragment;
    private CoinOperations mCoinsOperations;

    public CoinsFragmentController()
    {

    }

    public CoinsFragmentController(CoinsFragment cf)
    {
        mCoinsFragment = cf;
    }



}
