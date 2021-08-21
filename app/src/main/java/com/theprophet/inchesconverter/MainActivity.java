package com.theprophet.inchesconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private Button calculateButton;
    private EditText inchesEditText;
    private EditText metersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();

    }

    private void findViews(){
        resultText = findViewById(R.id.result);
        inchesEditText = findViewById(R.id.inches);
        metersEditText = findViewById(R.id.meters);
        calculateButton = findViewById(R.id.calculate_button);

    }


    private double convertToMeters(String inchesText){

        //integer variables

            int inches = Integer.parseInt(inchesText);

        //convert from inches to meters
        double inchesToMeters = inches/39.37;

        return inchesToMeters;
    }

    private double convertToInches(String metersText){


        //integer variables
        double meters = Double.parseDouble(metersText);

        //convert from inches to meters
        double metersToInches = meters * 39.37;

        return metersToInches;
    }

    private void displayResult(double conversion, String type){

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String myConversionResult = myDecimalFormatter.format(conversion);


            resultText.setText(myConversionResult + " " + type);

    }

    private void setupButtonClickListener(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setup logic based on which field is empty

                //String variables

                String metersText = metersEditText.getText().toString();
                String inchesText = inchesEditText.getText().toString();
                double conversionResult;



                try {
                    //convert to meters
                    if (metersText.isEmpty()) {

                        conversionResult = convertToMeters(inchesText);
                        displayResult(conversionResult, "meters");

                        //convert to inches
                    } else if (inchesText.isEmpty()) {
                        conversionResult = convertToInches(metersText);
                        displayResult(conversionResult, "inches");
                    } else { //if both fields have values
                        Toast.makeText(getApplicationContext(), "Please choose one type to convert.", Toast.LENGTH_SHORT).show();
                    }
                }catch(NumberFormatException ex){
                    Toast.makeText(getApplicationContext(),"Please enter a value to convert.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}