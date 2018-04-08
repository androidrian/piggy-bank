package com.example.filipe.piggybank.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filipe.piggybank.R;
import com.example.filipe.piggybank.Services.FileUtils;


public class TotalFragment extends Fragment {

    public final String TAG = "TotalFragment";

    private TextView mTotalIntegerPartTextView;
    private TextView mTotalDecimalPartTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, container, false);
        mTotalIntegerPartTextView = (TextView)view.findViewById(R.id.totalIntegerPartTextView);
        mTotalDecimalPartTextView = (TextView)view.findViewById(R.id.totalDecimalPartTextView);

        FileUtils services = new FileUtils();
        String[] data = services.readDataFromFile();

        Log.d(TAG,"Read Data");



        int position = 0;
        if(data != null){
            position = data.length-1;
            String totalValue = data[position];
            String totalIntDecPart[] = totalValue.split("\\.");
            if(totalIntDecPart.length>1) {
                Log.d(TAG, totalIntDecPart[0]);
                Log.d(TAG, totalIntDecPart[1]);
                String integerPart = getIntegerPart(totalIntDecPart[0]);
                String decimalPart = getDecimalPart(totalIntDecPart[1]);
                Log.d(TAG, "TOTAL VALUE: " + totalValue);
                Log.d(TAG, "INTEGER PART: " + integerPart);
                Log.d(TAG, "DECIMALPART: " + integerPart);
                mTotalIntegerPartTextView.setText(integerPart);
                mTotalDecimalPartTextView.setText(decimalPart);
            }else{
                String integerPart = getIntegerPart(totalIntDecPart[0]);
                Log.d(TAG, "TOTAL VALUE: " + totalValue);
                Log.d(TAG, "INTEGER PART: " + integerPart);
                Log.d(TAG, "DECIMALPART: " + integerPart);
                mTotalIntegerPartTextView.setText(integerPart);

            }
        }
        return view;
    }
    //problem aqui...se o inteiro tiver + que 1 digito
    //back to this later
    private String getIntegerPart(String totalValue)
    {
        return totalValue;
    }

    private String getDecimalPart(String totalValue)
    {
        return totalValue;
    }

}
