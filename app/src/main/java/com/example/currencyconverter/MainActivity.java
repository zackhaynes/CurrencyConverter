package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import  android.graphics.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static double amount;
    static double conversion;
    static double answer;
    static String currencyFrom;
    static String currencyTo;

    static int fromSelection;

    Boolean from = false;
    Boolean to = false;
    final static List<String> recentConvert = new ArrayList<>();

    // conversion method
    static void converter()
    {
        // converts amount to gold
        if(fromSelection == 1)
        {
            currencyFrom = " Copper = ";
            amount = amount * 0.100;
        }
        else if(fromSelection == 2)
        {
            currencyFrom = " Silver = ";
            amount = amount * 0.200;
        }
        else if(fromSelection == 3)
        {
            currencyFrom = " Gold = ";
        }
        // converts from gold to selected currency and rounds to 2 decimal place
        answer = Math.round((amount / conversion) *100.0) / 100.0;

    }

    // prints first 5 entries of list
    static String printList()
    {
        int counter = 0;
        String list = "";
        for(String entry : recentConvert)
        {
            counter++;
            list = list + entry;
            if(counter == 5)
            {
                break;
            }
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        Button buttonFromCopper = findViewById(R.id.buttonFromCopper);
        Button buttonFromSilver = findViewById(R.id.buttonFromSilver);
        Button buttonFromGold = findViewById(R.id.buttonFromGold);
        Button buttonToCopper = findViewById(R.id.buttonToCopper);
        Button buttonToSilver = findViewById(R.id.buttonToSilver);
        Button buttonToGold = findViewById(R.id.buttonToGold);
        Button buttonCalculate = findViewById(R.id.buttinCalculate);

        // EditText
        EditText textInput = findViewById(R.id.editTextAmount);

        // TextViews
        TextView textOutput = findViewById(R.id.textViewConvert);
        TextView textOutputHistory = findViewById(R.id.textViewConversions);


        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // no amount error
                if(textInput.getText().length() == 0)
                {
                    textOutput.setText("Please enter the amount you would like to convert");
                }
                // no error
                else if(from == true && to == true){
                    amount = Double.parseDouble(textInput.getText().toString());
                    converter();
                    textOutput.setText(textInput.getText().toString() + currencyFrom +  String.valueOf(answer) + currencyTo);
                    recentConvert.add(0, textOutput.getText().toString() + "\n");
                    textOutputHistory.setText(printList());

                    // reset
                    textInput.setText("");
                    from = false;
                    to = false;
                    buttonFromCopper.setTextColor(Color.BLACK);
                    buttonFromSilver.setTextColor(Color.BLACK);
                    buttonFromGold.setTextColor(Color.BLACK);
                    buttonToGold.setTextColor(Color.BLACK);
                    buttonToSilver.setTextColor(Color.BLACK);
                    buttonToCopper.setTextColor(Color.BLACK);
                }
                // no currency button error
                else
                {
                    textOutput.setText("Error please select the currencies you would like to convert");
                }
            }
        });

        buttonFromCopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFromCopper.setTextColor(Color.BLUE);
                buttonFromSilver.setTextColor(Color.BLACK);
                buttonFromGold.setTextColor(Color.BLACK);
                fromSelection = 1;
                from = true;
            }
        });

        buttonFromSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFromSilver.setTextColor(Color.BLUE);
                buttonFromCopper.setTextColor(Color.BLACK);
                buttonFromGold.setTextColor(Color.BLACK);
                fromSelection = 2;
                from = true;
            }
        });

        buttonFromGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFromGold.setTextColor(Color.BLUE);
                buttonFromSilver.setTextColor(Color.BLACK);
                buttonFromCopper.setTextColor(Color.BLACK);
                fromSelection = 3;
                from = true;
            }
        });

        buttonToCopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonToCopper.setTextColor(Color.BLUE);
                buttonToSilver.setTextColor(Color.BLACK);
                buttonToGold.setTextColor(Color.BLACK);
                currencyTo = " Copper ";
                conversion = 0.100;
                to = true;
            }
        });

        buttonToSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonToSilver.setTextColor(Color.BLUE);
                buttonToCopper.setTextColor(Color.BLACK);
                buttonToGold.setTextColor(Color.BLACK);
                currencyTo = " Silver ";
                conversion = 0.200;
                to = true;
            }
        });

        buttonToGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonToGold.setTextColor(Color.BLUE);
                buttonToSilver.setTextColor(Color.BLACK);
                buttonToCopper.setTextColor(Color.BLACK);
                currencyTo = " Gold ";
                conversion = 1;
                to = true;
            }
        });


    }
}
