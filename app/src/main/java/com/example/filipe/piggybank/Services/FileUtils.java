package com.example.filipe.piggybank.Services;

import android.content.Context;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Filipe on 11/02/2017.
 */

public class FileUtils {

    private String fileName = "teste.txt";
    private File root = Environment.getExternalStorageDirectory();
    private File dir = new File(root.getAbsolutePath() + "/piggybank");
    private File file = new File(dir,fileName);

   public FileUtils()
   {

   }

   public FileUtils(String string)
   {

       this.fileName = string;
   }

    public void loadValuesFromFile(List<EditText> editTextList)
    {
        String [] data = readDataFromFile();
        System.out.println("LOADING FROM FILE....");
        for(String s : data)
            System.out.println("\n" + s);
        EditText editText;
        String string;
        for(int i = 0; i < data.length-1; i++)
        {
            string = data[i];
            editText = editTextList.get(i);
            editText.setText(string);
        }
    }

    public String[] readDataFromFile()
    {

        String [] data = null;

        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            String line;

            line = sc.nextLine();
            data = line.split(";");

        } catch (FileNotFoundException ex) {
            System.out.println(fileName + " NOT FOUND.");

        }
        return data;
    }

    /**
     * Returns the value of the total as a string
     * @return the string representation of the Total var/value
     */
    private String getTotalAsString(TextView totalValue)
    {
        String string = totalValue.getText().toString();

        return string;
    }


    public void writeToSD(ArrayList<String> listOfEditextAsStrings, Context context,TextView totalValue)
    {
        boolean append = false;
        File file = createFile(context);

        try
        {
            FileOutputStream fos = new FileOutputStream(file,append);
            Toast.makeText(context, "Saving...", Toast.LENGTH_SHORT).show();
            for(String s : listOfEditextAsStrings)
            {
                fos.write(s.getBytes());
                fos.write((";").getBytes());
            }
            String total = getTotalAsString(totalValue);
            fos.write(total.getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(context,"SAVED!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"FILE NOT FOUND!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private File createFile(Context context)
    {
        String state;
        state = Environment.getExternalStorageState();
        File file = new File("","");

        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File filedir = new File(root.getAbsolutePath() + "/piggybank");

            if (!filedir.exists()) {//se o directorio nao existir
                filedir.mkdir(); //cria um
                Toast.makeText(context, "FILE CREATED!", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(context, "FILE ALREADY EXISTS!", Toast.LENGTH_SHORT).show();
            }
            file = new File(filedir, fileName);
        }else //if SD CARD not mounted
        {
            Toast.makeText(context,"SD card not found",Toast.LENGTH_SHORT).show();
        }

        return file;
    }

    public List<Integer> getFromFileListWithNumberOfCoinsInBank()
    {
        String[] data = readDataFromFile();
        List<Integer> listNumberOfCoins = new ArrayList<>();
       for(int i = 0; i < data.length-1; i++) {
           int value = Integer.parseInt(data[i]);
           listNumberOfCoins.add(value);
       }

        return listNumberOfCoins;
    }


}


