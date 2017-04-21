package com.example.filipe.piggybank.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipe.piggybank.Model.CoinOperations;
import com.example.filipe.piggybank.R;
import com.example.filipe.piggybank.Services.FileUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoinsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CoinsFragment";

    final double VALUE_COIN_1 = 2.0;
    final double VALUE_COIN_2 = 1.0;
    final double VALUE_COIN_3 = 0.50;
    final double VALUE_COIN_4 = 0.20;
    final double VALUE_COIN_5 = 0.10;
    final double VALUE_COIN_6 = 0.05;
    final double VALUE_COIN_7 = 0.02;
    final double VALUE_COIN_8 = 0.01;

    private String DEFAULT_VALUE = "0"; //value to be set at the beguinning of the app or reset
    private TextView totalValueTextView;
    private EditText numberCoins1, numberCoins2, numberCoins3, numberCoins4, numberCoins5, numberCoins6, numberCoins7, numberCoins8;
    private Button incButtonCoin1,incButtonCoin2,incButtonCoin3,incButtonCoin4,incButtonCoin5,incButtonCoin6,incButtonCoin7,incButtonCoin8;
    private Button decButtonCoin1,decButtonCoin2,decButtonCoin3,decButtonCoin4,decButtonCoin5,decButtonCoin6,decButtonCoin7,decButtonCoin8;
    private Button resetButton, loadButton, saveButton;

    private List<Double> m_listOfValues = new ArrayList<>();
    private List<Button> m_listOfButtons = new ArrayList<>();
    private SimpleArrayMap<Integer,Integer> m_mapOfIndexToCoinTextView = new SimpleArrayMap<>();
    private SimpleArrayMap<EditText,Integer> m_mapOfEditTextIndex = new SimpleArrayMap<>();
    private SimpleArrayMap<Button,EditText> m_mapOfButtonToEditText = new SimpleArrayMap<>();

    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coins_layout, container, false);

        initWidgetComponents(view);
        setDefaultValuesToNumberOfCoinsEditText();
        setListOfValueOfCoins();
        setmMapOfIndexToCoinTextView();
        setMapOfEditTextIndex();
        setListOfButtons();
        setMapOfButtonToEditText();

//        setOnClickList();
        setButtonListeners();

        return view;
    }

    @Override
    public void onClick(View v) {

        String idAsString = v.getResources().getResourceEntryName(v.getId());
        String buttonName = "null";
        String func = idAsString.substring(0,3);
        if(func.equalsIgnoreCase("inc"))
        {
             buttonName = "incrementButton";

        }else if(func.equalsIgnoreCase("dec"))
        {
            buttonName = "decrementButton";
        }
        double totalValueUpdate = 0;
        switch (buttonName) {
            case "incrementButton":
                Toast.makeText(getContext(),func,Toast.LENGTH_SHORT).show();
//                totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
                break;
            case "decrementButton":
                Toast.makeText(getContext(),func,Toast.LENGTH_SHORT).show();
//                totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
                break;
            case "null":
                Toast.makeText(getContext(),func,Toast.LENGTH_SHORT).show();
            default:
                Toast.makeText(getContext(),"UNKNOWN BUTTON",Toast.LENGTH_SHORT).show();

        }

    }


    public double updateTotalValue(View v, EditText numberOfCoinsEditText)
    {

        CoinOperations co = new CoinOperations();
        String idAsString,nameTypeOfButton,numberOfCoinsString;

//        int name = v.getId();
//        int id = 0;
//        EditText numberOfCoinsEditText = null;
//        for(int i = 0; i < m_mapOfButtonToEditText.size(); i++)
//        {
//            id = m_mapOfButtonToEditText.keyAt(i).getId();
//            System.out.println("ID: "+id);
//            System.out.println("NAME: " + name);
//            if( id == name)
//            {
//                System.out.println("PASSA AQUI");
//                numberOfCoinsEditText = m_mapOfButtonToEditText.get(i);
//            }
//
//        }
//
//        if(numberOfCoinsEditText == null)
//        {
//            System.out.println("EDITTEXT IS NULL!");
//        }

        idAsString = v.getResources().getResourceEntryName(v.getId());
        nameTypeOfButton = idAsString.substring(0,3);//"inc" or "dec" of increment and decrement
        numberOfCoinsString = numberOfCoinsEditText.getText().toString();
        System.out.println("NUMBER OF COINS: " + numberOfCoinsString);
        //index--;
        //guarda o valor das moedas antes de o alterar
        int totalNumberOfCoinsBefore;

        if(numberOfCoinsString.equalsIgnoreCase(" "))
        {
            totalNumberOfCoinsBefore = 0;
            System.out.println("TOTAL NUMBER OF COINS BEFORE: " + totalNumberOfCoinsBefore);
        }
        else
        {
            totalNumberOfCoinsBefore = Integer.valueOf(numberOfCoinsString);
            System.out.println("TOTAL NUMBER OF COINS BEFORE: " + totalNumberOfCoinsBefore);
        }

        //e agora muda o campo e actualiza o valor de acordo com o botao
        int totalNumberOfCoinsAfter = 0;
        if(nameTypeOfButton.equalsIgnoreCase("inc")) {
            System.out.println("INCREMENT");
            totalNumberOfCoinsAfter = (int) co.addCoinToEditText(numberOfCoinsEditText);
            numberOfCoinsEditText.setText(String.valueOf(totalNumberOfCoinsAfter));
        }
        if(nameTypeOfButton.equalsIgnoreCase("dec"))
        {
            System.out.println("DECREMENT");
            totalNumberOfCoinsAfter = (int) co.takeCoinFromEditText(numberOfCoinsEditText);
            numberOfCoinsEditText.setText(String.valueOf(totalNumberOfCoinsAfter));
        }

        //parte final do update

        System.out.println("TOTAL NUMBER OF COINS AFTER: " + totalNumberOfCoinsAfter);

        int diff =
                totalNumberOfCoinsAfter - totalNumberOfCoinsBefore;
        System.out.println("DIFF: " + diff);

        int correspondingValueIndex = getMapOfEditTextIndex().get(numberOfCoinsEditText);
        double diffOfValue = diff * m_listOfValues.get(correspondingValueIndex);
        double totalValueBefore = Double.valueOf(totalValueTextView.getText().toString());
        double totalValueUpdate = totalValueBefore + diffOfValue;

        return totalValueUpdate;
    }

    private void setOnClickList()
    {
        incButtonCoin1.setOnClickListener(this);
        incButtonCoin2.setOnClickListener(this);
        incButtonCoin3.setOnClickListener(this);
        incButtonCoin4.setOnClickListener(this);
        incButtonCoin5.setOnClickListener(this);
        incButtonCoin6.setOnClickListener(this);
        incButtonCoin7.setOnClickListener(this);
        incButtonCoin8.setOnClickListener(this);

        decButtonCoin1.setOnClickListener(this);
        decButtonCoin2.setOnClickListener(this);
        decButtonCoin3.setOnClickListener(this);
        decButtonCoin4.setOnClickListener(this);
        decButtonCoin5.setOnClickListener(this);
        decButtonCoin6.setOnClickListener(this);
        decButtonCoin7.setOnClickListener(this);
        decButtonCoin8.setOnClickListener(this);




    }


    private void setButtonListeners()
    {
        incButtonCoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate;

                totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins1)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));

            }
        });

        incButtonCoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins2)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        incButtonCoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins3)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        incButtonCoin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins4)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins5)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins6)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins7)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins8)));
                totalValueTextView.setText(String.valueOf(totalValueUpdate));

            }
        });


        /////////////////////////////////////////////////////////////
        decButtonCoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins1);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins2);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins3);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins4);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins5);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins6);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins7);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalValueUpdate = updateTotalValue(v,numberCoins8);
                totalValueTextView.setText(String.valueOf(totalValueUpdate));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils fileService = new FileUtils();
                fileService.loadValuesFromFile(getListWithEditTexts());
                String[] data = fileService.readDataFromFile();
                totalValueTextView.setText(data[data.length-1]);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils service = new FileUtils();
                service.writeToSD(getListOfEditTextAsString(),v.getContext(), totalValueTextView);
            }
        });

    }


    private void setListOfValueOfCoins()
    {
        m_listOfValues = Arrays.asList(VALUE_COIN_1,VALUE_COIN_2,VALUE_COIN_3,VALUE_COIN_4,VALUE_COIN_5,VALUE_COIN_6,VALUE_COIN_7,VALUE_COIN_8);

    }

    private void initWidgetComponents(View view)
    {
        //initialize EditTexts
        numberCoins1 = (EditText) view.findViewById(R.id.coinText1);
        numberCoins2 = (EditText) view.findViewById(R.id.coinText2);
        numberCoins3 = (EditText) view.findViewById(R.id.coinText3);
        numberCoins4 = (EditText) view.findViewById(R.id.coinText4);
        numberCoins5 = (EditText) view.findViewById(R.id.coinText5);
        numberCoins6 = (EditText) view.findViewById(R.id.coinText6);
        numberCoins7 = (EditText) view.findViewById(R.id.coinText7);
        numberCoins8 = (EditText) view.findViewById(R.id.coinText8);

        //initialize button of addition
        incButtonCoin1 = (Button) view.findViewById(R.id.incButton1);
        incButtonCoin2 = (Button) view.findViewById(R.id.incButton2);
        incButtonCoin3 = (Button) view.findViewById(R.id.incButton3);
        incButtonCoin4 = (Button) view.findViewById(R.id.incButton4);
        incButtonCoin5 = (Button) view.findViewById(R.id.incButton5);
        incButtonCoin6 = (Button) view.findViewById(R.id.incButton6);
        incButtonCoin7 = (Button) view.findViewById(R.id.incButton7);
        incButtonCoin8 = (Button) view.findViewById(R.id.incButton8);

        List<Integer> list = new ArrayList<>();
        list.add(R.id.incButton1);



        //initialize buttons of subtraction
        decButtonCoin1 = (Button) view.findViewById(R.id.decButton1);
        decButtonCoin2 = (Button) view.findViewById(R.id.decButton2);
        decButtonCoin3 = (Button) view.findViewById(R.id.decButton3);
        decButtonCoin4 = (Button) view.findViewById(R.id.decButton4);
        decButtonCoin5 = (Button) view.findViewById(R.id.decButton5);
        decButtonCoin6 = (Button) view.findViewById(R.id.decButton6);
        decButtonCoin7 = (Button) view.findViewById(R.id.decButton7);
        decButtonCoin8 = (Button) view.findViewById(R.id.decButton8);

        //initialize TextViews
        totalValueTextView = (TextView) view.findViewById(R.id.totalTextView);

        //options buttons
        resetButton = (Button) view.findViewById(R.id.resetButton);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        loadButton = (Button) view.findViewById(R.id.loadButton);
    }

    public ArrayList<String> getListOfEditTextAsString()
    {
        ArrayList<String> listOfEditTextAsString = new ArrayList<>();

        String value;
        for(EditText e : getListWithEditTexts())
        {
            value = e.getText().toString();
            listOfEditTextAsString.add(value);
        }

        return listOfEditTextAsString;

    }

    private void setMapOfButtonToEditText()
    {
        int i = 0;
        int limit = getListWithEditTexts().size();
        for(Button b : getListWithButtons())
        {
            m_mapOfButtonToEditText.put(b,getListWithEditTexts().get(i));
            i++;
            if(i == limit)
            {
                i = 0;
            }
        }
    }
    private void setListOfButtons()
    {
        m_listOfButtons = Arrays.asList(incButtonCoin1,incButtonCoin2,incButtonCoin3,incButtonCoin4,incButtonCoin5,
                incButtonCoin6,incButtonCoin7,incButtonCoin8,decButtonCoin1,decButtonCoin2,decButtonCoin3,decButtonCoin4,decButtonCoin5,
                decButtonCoin6,decButtonCoin7,decButtonCoin8);
    }

    private List<Button> getListWithButtons()
    {
        return m_listOfButtons;
    }

    private void setDefaultValuesToNumberOfCoinsEditText()
    {
        EditText editText;
        for(int i = 0; i< getListWithEditTexts().size(); i++)
        {
            editText = getListWithEditTexts().get(i);
            editText.setText(DEFAULT_VALUE);
        }


    }

    private void resetValues()
    {
        setDefaultValuesToNumberOfCoinsEditText();
        totalValueTextView.setText(DEFAULT_VALUE);
    }
    private void setmMapOfIndexToCoinTextView()
    {
        int i = 1;
        for(EditText editText : getListWithEditTexts())
        {
            m_mapOfIndexToCoinTextView.put(editText.getId(),i);
            i++;
        }

    }

    private void setMapOfEditTextIndex()
    {
        int i = 0;
        for(EditText t : getListWithEditTexts())
        {
            m_mapOfEditTextIndex.put(t,i);
            i++;
        }

    }

    public SimpleArrayMap<EditText,Integer> getMapOfEditTextIndex()
    {
        return m_mapOfEditTextIndex;
    }

    private List<EditText> getListWithEditTexts()
    {
        List<EditText> list =
                Arrays.asList(numberCoins1,numberCoins2,numberCoins3,numberCoins4,
                        numberCoins5,numberCoins6,numberCoins7,numberCoins8);
        return list;
    }

    private List<Button> setListOfIncrementButtons()
    {
        List<Button> l_incButtons =
                Arrays.asList(incButtonCoin1,incButtonCoin2,incButtonCoin3,incButtonCoin4,
                        incButtonCoin4,incButtonCoin5,incButtonCoin6,incButtonCoin7,incButtonCoin8);
        return l_incButtons;
    }


}
