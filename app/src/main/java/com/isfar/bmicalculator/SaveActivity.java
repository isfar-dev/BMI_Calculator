package com.isfar.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SaveActivity extends AppCompatActivity {

    public static TextView tvSave;
    AppCompatButton bClear;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        tvSave = findViewById(R.id.tvSave);
        bClear = findViewById(R.id.bClear);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        
        String nameString = sharedPreferences.getString("name", "null..");
        String savedString = sharedPreferences.getString("saved", "null...");

        tvSave.append("\nDear " + nameString + "\n" + savedString);


        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvSave.setText("");

            }
        });


    }
}