package com.example.filipe.piggybank;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipe.piggybank.Utils.CalculationsUtils;
import com.example.filipe.piggybank.Utils.Services;

import net.danlew.android.joda.JodaTimeAndroid;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity implements TextWatcher {

    ViewPager viewPager;

    final double VALUE_COIN_1 = 2.0;
    final double VALUE_COIN_2 = 1.0;
    final double VALUE_COIN_3 = 0.50;
    final double VALUE_COIN_4 = 0.20;
    final double VALUE_COIN_5 = 0.10;
    final double VALUE_COIN_6 = 0.05;
    final double VALUE_COIN_7 = 0.02;
    final double VALUE_COIN_8 = 0.01;

    private String DEFAULT_VALUE = "0"; //value to be set at the beguinning of the app or reset
    private TextView coinValue1,coinValue2,coinValue3,coinValue4,coinValue5,coinValue6,coinValue7,coinValue8,totalValue;
    private TextView predictionTextView;
    private EditText numberCoins1, numberCoins2, numberCoins3, numberCoins4, numberCoins5, numberCoins6, numberCoins7, numberCoins8;
    private Button incButtonCoin1,incButtonCoin2,incButtonCoin3,incButtonCoin4,incButtonCoin5,incButtonCoin6,incButtonCoin7,incButtonCoin8;
    private Button decButtonCoin1,decButtonCoin2,decButtonCoin3,decButtonCoin4,decButtonCoin5,decButtonCoin6,decButtonCoin7,decButtonCoin8;
    private Button resetButton, loadButton, saveButton, statsButton;

    private List<Double> l_values = new ArrayList<>();
    ArrayList<Integer> l_absoluteFr = new ArrayList<>();


    private String fileName = "piggyBankRecords.txt";
    private File root = Environment.getExternalStorageDirectory();
    private File dir = new File(root.getAbsolutePath() + "/piggybank");
    private File file = new File(dir,fileName);

    private DecimalFormat df = new DecimalFormat("#.00");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //joda-time initialization
        JodaTimeAndroid.init(this);

        initComponents();
        setComponents();
        setButtonListeners();

        setListOfValueOfCoins();
        List<EditText> listWithEditText = getListWithEditTexts();
        //setListOfDecrementButtons();

        //Services reader = new Services();

        for(EditText e : listWithEditText)
        {
            e.addTextChangedListener(this);
        }

        if (shouldAskPermissions()) {
            askPermissions();
        }

    }



    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }



    /**
     * Convert a text in an EditText field to a string
     * @param editText the number of coins
     * @return a string with the representation of number of coins in editText
     */
    private String convertEditTextToString(EditText editText)
    {
        String s;
        s = editText.getText().toString();

        return s;
    }

    /**
     * Returns the value of the total as a string
     * @return the string representation of the Total var/value
     */
    private String getTotalAsString(TextView totalValue)
    {
        String string;
        string = totalValue.getText().toString();

        return string;
    }



    private void setListOfValueOfCoins()
    {
        l_values = Arrays.asList(VALUE_COIN_1,VALUE_COIN_2,VALUE_COIN_3,VALUE_COIN_4,VALUE_COIN_5,VALUE_COIN_6,VALUE_COIN_7,VALUE_COIN_8);

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

    //back to this later, just to generalize a method to set the listeners


    public int getCorrespondingEditTextValueIndex(View v) {
        int index = 0;

        switch (v.getId()) {
            case R.id.coinText1:
                index = 0;
                break;

            case R.id.coinText2:
                index = 1;
                break;

            case R.id.coinText3:
                index = 2;
                break;

            case R.id.coinText4:
                index = 3;
                break;

            case R.id.coinText5:
                index = 4;
                break;

            case R.id.coinText6:
                index = 5;
                break;

            case R.id.coinText7:
                index = 6;
                break;

            case R.id.coinText8:
                index = 7;
                break;
        }
        return index;
    }

    private void setButtonListeners()
    {

        incButtonCoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = 1;
                double totalValueUpdate;
                totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins1)));


                totalValue.setTag("TRIGGER");
                totalValue.setText("GENESIS");
                totalValue.setTag(null);

                totalValue.setText(String.valueOf(totalValueUpdate));

            }
        });

        incButtonCoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 2;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins2)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        incButtonCoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 3;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins3)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        incButtonCoin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 4;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins4)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 5;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins5)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 6;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins6)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 7;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins7)));
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        incButtonCoin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 8;
                double totalValueUpdate = Double.valueOf(df.format(updateTotalValue(v,numberCoins8)));
                totalValue.setText(String.valueOf(totalValueUpdate));

            }
        });


        /////////////////////////////////////////////////////////////
        decButtonCoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 1;
                double totalValueUpdate = updateTotalValue(v,numberCoins1);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 2;
                double totalValueUpdate = updateTotalValue(v,numberCoins2);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 3;
                double totalValueUpdate = updateTotalValue(v,numberCoins3);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        decButtonCoin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 4;
                double totalValueUpdate = updateTotalValue(v,numberCoins4);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 5;
                double totalValueUpdate = updateTotalValue(v,numberCoins5);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 6;
                double totalValueUpdate = updateTotalValue(v,numberCoins6);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 7;
                double totalValueUpdate = updateTotalValue(v,numberCoins7);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });
        decButtonCoin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 8;
                double totalValueUpdate = updateTotalValue(v,numberCoins8);
                totalValue.setText(String.valueOf(totalValueUpdate));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComponents();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services services = new Services(fileName);
                services.loadFromFile(getListWithEditTexts());
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services service = new Services();
                service.writeToSD(getListOfEditTextAsString(),getApplicationContext(),totalValue);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StatsActivity.class);
                ArrayList<Integer> l_absoluteValues = setListWithAbsoluteFrequencyOfCoins(getListWithEditTexts());
                System.out.println("LIST WITH ABSOLUTE FREQUENCY VALUES:");
                System.out.println(getListWithAbsoluteFrequencyOfCoins());
                intent.putIntegerArrayListExtra("numberOfCoins",l_absoluteValues);
                startActivity(intent);
            }
        });



    }
    private void initComponents()
    {
        //initialize EditTexts
        numberCoins1 = (EditText) findViewById(R.id.coinText1);
        numberCoins2 = (EditText) findViewById(R.id.coinText2);
        numberCoins3 = (EditText) findViewById(R.id.coinText3);
        numberCoins4 = (EditText) findViewById(R.id.coinText4);
        numberCoins5 = (EditText) findViewById(R.id.coinText5);
        numberCoins6 = (EditText) findViewById(R.id.coinText6);
        numberCoins7 = (EditText) findViewById(R.id.coinText7);
        numberCoins8 = (EditText) findViewById(R.id.coinText8);

        //initialize button of addition
        incButtonCoin1 = (Button) findViewById(R.id.incButton1);
        incButtonCoin2 = (Button) findViewById(R.id.incButton2);
        incButtonCoin3 = (Button) findViewById(R.id.incButton3);
        incButtonCoin4 = (Button) findViewById(R.id.incButton4);
        incButtonCoin5 = (Button) findViewById(R.id.incButton5);
        incButtonCoin6 = (Button) findViewById(R.id.incButton6);
        incButtonCoin7 = (Button) findViewById(R.id.incButton7);
        incButtonCoin8 = (Button) findViewById(R.id.incButton8);

        //initialize buttons of subtraction
        decButtonCoin1 = (Button) findViewById(R.id.decButton1);
        decButtonCoin2 = (Button) findViewById(R.id.decButton2);
        decButtonCoin3 = (Button) findViewById(R.id.decButton3);
        decButtonCoin4 = (Button) findViewById(R.id.decButton4);
        decButtonCoin5 = (Button) findViewById(R.id.decButton5);
        decButtonCoin6 = (Button) findViewById(R.id.decButton6);
        decButtonCoin7 = (Button) findViewById(R.id.decButton7);
        decButtonCoin8 = (Button) findViewById(R.id.decButton8);

        //initialize TextViews
        totalValue = (TextView) findViewById(R.id.totalTextView);

        //options buttons

        resetButton = (Button) findViewById(R.id.resetButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        loadButton = (Button) findViewById(R.id.loadButton);
        statsButton = (Button) findViewById(R.id.statsButton);

        predictionTextView = (TextView) findViewById(R.id.predictionTextView);
    }

    private void setComponents()
    {
        EditText editText;
        for(int i = 0; i< getListWithEditTexts().size(); i++)
        {
            editText = getListWithEditTexts().get(i);
            editText.setText(DEFAULT_VALUE);
        }


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

    public double updateTotalValue(View v, EditText editText)
    {

        String idAsString,nameTypeOfButton,numberOfCoins;
        int totalNumberOfCoinsBefore;

        idAsString = v.getResources().getResourceEntryName(v.getId());
        nameTypeOfButton = idAsString.substring(0,3);//"inc" or "dec" of increment and decrement
        numberOfCoins = editText.getText().toString();

        //index--;

        if(numberOfCoins.equalsIgnoreCase(""))
        {
            totalNumberOfCoinsBefore = 0;
        }
        else
        {
            totalNumberOfCoinsBefore = Integer.valueOf(numberOfCoins);
        }

        if(nameTypeOfButton.equalsIgnoreCase("inc")) {
            editText.setText(String.valueOf((int) addCoin(editText)));
        }
        if(nameTypeOfButton.equalsIgnoreCase("dec"))
        {
            editText.setText(String.valueOf((int) takeCoin(editText)));
        }

        int totalNumberOfCoinsAfter = Integer.valueOf(numberOfCoins);

        int diff = totalNumberOfCoinsAfter - totalNumberOfCoinsBefore;
        int correspondingValueIndex = getCorrespondingEditTextValueIndex(v);
        double diffOfValue = diff * l_values.get(correspondingValueIndex);
        double totalValueBefore = Double.valueOf(totalValue.getText().toString());
        double totalValueUpdate = totalValueBefore + diffOfValue;

        //Predition prototype BEGIN
        Prediction p = new Prediction();
        int day1 = 1;
        int day2 = 13;
        double totalPredictionValue = p.getTotalPreditionOnTargetDay(totalValueUpdate,day1,day2);
        String totalPrediction = String.valueOf(df.format(totalPredictionValue));
        predictionTextView.setText(totalPrediction);
        //Prototype END

        return totalValueUpdate;
    }


    //this method is buggy
    //este metodo talvez devesse ser void apenas para alterar o valor do total
    // e depois arranjar outro metodo para devolver
    public double addCoin(EditText editText){
        double total;
        String numberOfCoinsText = editText.getText().toString();

        //se nao tiver nada para nao dar null tem que ficar a zero
        //ou entao fazer catch do NullPointerException
        if(numberOfCoinsText.equalsIgnoreCase(""))
        {
            //total = 0;
            editText.setText(DEFAULT_VALUE);
            total = Integer.valueOf(numberOfCoinsText);
        }
        else
        {
            total = Integer.valueOf(numberOfCoinsText);
            total++;
        }

        return total;
    }

    public double takeCoin(EditText editText){
        double total;
        String numberOfCoins;

        numberOfCoins = editText.getText().toString();

        total = Integer.valueOf(numberOfCoins);
        total--;

        if(total < 0 )
        {
            total = 0;
        }

        return total;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

//        validar se o input de user ou programaticamente
//        numberCoins1.removeTextChangedListener(this);
//        String empty = "0";
//        numberCoins1.addTextChangedListener(this);



    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        double total;
        CalculationsUtils calc = new CalculationsUtils();
        total = calc.getTotalSumOfCoins(getListWithEditTexts(),l_values);
        totalValue.setText(String.valueOf(total));
        Toast.makeText(this,"afterTextChanged",Toast.LENGTH_LONG);

    }
    private ArrayList<Integer> setListWithAbsoluteFrequencyOfCoins(List<EditText> list)
    {
        ArrayList<Integer> l_absoluteFr = new ArrayList<>();
        for(EditText e: list)
        {
            int totalCoin = Integer.parseInt(e.getText().toString());
            l_absoluteFr.add(totalCoin);
        }
        return l_absoluteFr;
    }

    private ArrayList<Integer> getListWithAbsoluteFrequencyOfCoins()
    {
        return l_absoluteFr;
    }
}
