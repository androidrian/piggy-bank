package com.example.filipe.piggybank.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.filipe.piggybank.Services.StatsOperations;
import com.example.filipe.piggybank.R;
import com.example.filipe.piggybank.Services.FileUtils;
import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {

    private static final String TAG = "StatsFragment";

    private TextView coin1,coin2,coin3,coin4,coin5,coin6,coin7,coin8;
    private TextView coinPerc1,coinPerc2,coinPerc3,coinPerc4,coinPerc5,coinPerc6,coinPerc7,coinPerc8;
    private ArrayList<TextView> l_coinsTextView;
    private ArrayList<TextView> l_percentageTextView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_layout, container, false);
        Log.d(TAG,"onCreateView: Starting...");

        initComponents(view);
        FileUtils services = new FileUtils();

        List<Integer> listNumberOfCoins = services.getFromFileListWithNumberOfCoinsInBank();
        setCoinPercentageValuesOnTextViews(listNumberOfCoins);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated: Starting...");
//       initComponents(view);
    }

    private void initComponents(View view)
    {
        coin1 = (TextView) view.findViewById(R.id.statsTextView1);
        coin2 = (TextView) view.findViewById(R.id.statsTextView2);
        coin3 = (TextView) view.findViewById(R.id.statsTextView3);
        coin4 = (TextView) view.findViewById(R.id.statsTextView4);
        coin5 = (TextView) view.findViewById(R.id.statsTextView5);
        coin6 = (TextView) view.findViewById(R.id.statsTextView6);
        coin7 = (TextView) view.findViewById(R.id.statsTextView7);
        coin8 = (TextView) view.findViewById(R.id.statsTextView8);

        coinPerc1 = (TextView) view.findViewById(R.id.statsTextViewPerc1);
        coinPerc2 = (TextView) view.findViewById(R.id.statsTextViewPerc2);
        coinPerc3 = (TextView) view.findViewById(R.id.statsTextViewPerc3);
        coinPerc4 = (TextView) view.findViewById(R.id.statsTextViewPerc4);
        coinPerc5 = (TextView) view.findViewById(R.id.statsTextViewPerc5);
        coinPerc6 = (TextView) view.findViewById(R.id.statsTextViewPerc6);
        coinPerc7 = (TextView) view.findViewById(R.id.statsTextViewPerc7);
        coinPerc8 = (TextView) view.findViewById(R.id.statsTextViewPerc8);

        l_coinsTextView = new ArrayList<>();
        l_coinsTextView.add(coin1);
        l_coinsTextView.add(coin2);
        l_coinsTextView.add(coin3);
        l_coinsTextView.add(coin4);
        l_coinsTextView.add(coin5);
        l_coinsTextView.add(coin6);
        l_coinsTextView.add(coin7);
        l_coinsTextView.add(coin8);

        l_percentageTextView = new ArrayList<>();
        l_percentageTextView.add(coinPerc1);
        l_percentageTextView.add(coinPerc2);
        l_percentageTextView.add(coinPerc3);
        l_percentageTextView.add(coinPerc4);
        l_percentageTextView.add(coinPerc5);
        l_percentageTextView.add(coinPerc6);
        l_percentageTextView.add(coinPerc7);
        l_percentageTextView.add(coinPerc8);

    }

    private void setCoinPercentageValuesOnTextViews(List<Integer> listNumberOfCoins)
    {
        StatsOperations stats = new StatsOperations();
        ArrayList<Double> listRelativeFr = stats.getListWithRelativeFrequencyOfCoins(listNumberOfCoins);
        ArrayList<Double> listWithPercentageValues  = stats.setListWithPercentageOfCoins(listRelativeFr);

        if(!listWithPercentageValues.isEmpty()) {
            TextView textView;
            String string;
            for(int i = 0; i < l_percentageTextView.size(); i++)
            {
                textView = l_percentageTextView.get(i);
                string = String.valueOf(listWithPercentageValues.get(i));
                textView.setText(string);
            }

        } else {
            System.out.println("EMPTY LIST");

        }
    }


}
