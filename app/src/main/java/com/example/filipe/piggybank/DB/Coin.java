package com.example.filipe.piggybank.DB;

/**
 * Created by Filipe on 23/04/2017.
 */

public class Coin {

    private int id;
    private String m_coinName;

    public Coin()
    {

    }

    public Coin(String coinName)
    {
        this.m_coinName = coinName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getM_coinName() {
        return m_coinName;
    }

}

