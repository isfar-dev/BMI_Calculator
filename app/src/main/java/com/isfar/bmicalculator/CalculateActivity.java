package com.isfar.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CalculateActivity extends AppCompatActivity {

    CheckBox cbMale, cbFemale;
    TextInputEditText etkg, etFeet, etInch;
    AppCompatButton bCal, bSave;
    TextView tvResult_1, tvResult_2;
    EditText edName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        cbMale = findViewById(R.id.cbMale);
        cbFemale = findViewById(R.id.cbFemale);
        etkg = findViewById(R.id.etkg);
        etFeet = findViewById(R.id.etFeet);
        etInch = findViewById(R.id.etInch);
        bCal = findViewById(R.id.bCal);
        edName = findViewById(R.id.edName);
        bSave = findViewById(R.id.bSave);
        tvResult_1 = findViewById(R.id.tvResult_1);
        tvResult_2 = findViewById(R.id.tvResult_2);


        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


//##################################################################################################
        bCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//***************************************************************************************
                if (etkg.length()<=0 || etFeet.length()<=0 || etInch.length()<=0) {

                    if (etkg.length()<=0) {
                        etkg.setError("please enter a Weight");
                    }
                    if (etFeet.length()<=0) {
                        etFeet.setError("please enter a Height");
                    }
                    if (etInch.length()<=0) {
                        etInch.setError("please enter a Height");
                    }

                } else {

                    Integer kg = Integer.parseInt(etkg.getText().toString());
                    Integer feet = Integer.parseInt(etFeet.getText().toString());
                    Integer inch = Integer.parseInt(etInch.getText().toString());

                    Float meterFeet = (float) (feet/3.281);
                    Float meterInch = (float) (inch/39.37);
                    float totalMeter = meterFeet+meterInch;

                    Float bmi = kg/(totalMeter*totalMeter);

    //********************************************************************************
                    if (bmi < 18.5) {

                        tvResult_1.setText("Underweight");
                        tvResult_1.setTextColor(Color.RED);

                        tvResult_2.setText("Your BMI is: " + bmi + "\n\nand you are underweight. You have to gain your weight. Eat a high-calorie diet and exercise regularly. Best of luck.");

                    } else if (bmi >= 18.5 && bmi <= 24.9) {

                        tvResult_1.setText("Healthy Weight");
                        tvResult_1.setTextColor(Color.GREEN);

                        tvResult_2.setText("Your BMI is: " + bmi + "\n\nand you are normal. Live a healthy life and try to maintain it. Best of luck.");

                    } else if (bmi >= 25 && bmi <= 29.9) {

                        tvResult_1.setText("Overweight");
                        tvResult_1.setTextColor(Color.RED);

                        tvResult_2.setText("Your BMI is: " + bmi + "\n\nand you are overweight. You need to lose weight. Follow a regular diet and do exercise. Best of luck.");

                    } else if (bmi >= 30) {

                        tvResult_1.setText("Obesity");
                        tvResult_1.setTextColor(Color.RED);

                        tvResult_2.setText("Your BMI is: " + bmi + "\n\noh no!! you are obese. You need to lose weight imargency. Follow a regular diet and do exercise. Best of luck.");

                    }
    //********************************************************************************

                    edName.setVisibility(View.VISIBLE);
                    bSave.setVisibility(View.VISIBLE);

                }

//*******************************************************************************

            }
        });

//##################################################################################################

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edName.length() <= 0) {
                    edName.setError("Please enter a name");
                    Toast.makeText(CalculateActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    String stringName = edName.getText().toString();
                    String myString = tvResult_2.getText().toString();
                    editor.putString("name", ""+stringName);
                    editor.putString("saved", ""+myString);
                    editor.apply();

                }
            }
        });








    }
}