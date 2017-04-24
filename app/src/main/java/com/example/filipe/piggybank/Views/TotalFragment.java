package com.example.filipe.piggybank.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        int position = data.length-1;
        String totalValue = data[position];
        String integerPart = getIntegerPart(totalValue);
        String decimalPart = getDecimalPart(totalValue);
        mTotalIntegerPartTextView.setText(integerPart);
        mTotalDecimalPartTextView.setText(decimalPart);

        return view;

    }
    //problem aqui...se o inteiro tiver + que 1 digito
    //back to this later
    private String getIntegerPart(String totalValue)
    {
        return totalValue.substring(0,1);
    }

    private String getDecimalPart(String totalValue)
    {
        return totalValue.substring(1,4);
    }

}
