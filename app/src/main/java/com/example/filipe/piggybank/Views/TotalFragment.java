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

    public final String TAG = getClass().getName();

    private TextView mTotalIntegerPartTextView;
    private TextView mTotalDecimalPartTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        FileUtils services = new FileUtils();
        String[] data = services.readDataFromFile();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, container, false);
        mTotalIntegerPartTextView = (TextView)view.findViewById(R.id.totalIntegerPartTextView);
        mTotalDecimalPartTextView = (TextView)view.findViewById(R.id.totalDecimalPartTextView);

        int position;
        if(data != null){
            position = data.length-1;
            String totalValue = data[position];
            String totalIntDecPart[] = totalValue.split("\\.");
            if(totalIntDecPart.length>1) {
                String integerPart = getIntegerPart(totalIntDecPart);
                String decimalPart = getDecimalPart(totalIntDecPart);
                mTotalIntegerPartTextView.setText(integerPart);
                mTotalDecimalPartTextView.setText(decimalPart);
            }else{
                String integerPart = getIntegerPart(totalIntDecPart);
                mTotalIntegerPartTextView.setText(integerPart);
            }
        }
        return view;
    }

    /**
     *
     * @param totalValue
     * @return
     */
    private String getIntegerPart(String[] totalValue)
    {
        return totalValue[0];
    }

    /**
     *
     * @param totalValue
     * @return
     */
    private String getDecimalPart(String[] totalValue)
    {
        return totalValue[1];
    }

}
