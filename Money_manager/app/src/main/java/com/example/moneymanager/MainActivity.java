package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button expenseButton = (Button)findViewById(R.id.button_enter_expense);
        Button incomeButton = (Button)findViewById(R.id.button_enter_income);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, /*ExpenseScreen.class*/Uri.parse("https://www.youtube.com/"));
                Intent intent = new Intent(MainActivity.this, ExpenseScreen.class);
                startActivity(intent);
            }
        });
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IncomeScreen.class);
                startActivity(intent);
            }
        });
    }

}