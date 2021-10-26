package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button expenseButton = (Button)findViewById(R.id.button_enter_expense);
//        Button incomeButton = (Button)findViewById(R.id.button_enter_income);
        Button inputScreenButton = (Button)findViewById(R.id.button_input_page);
        Button summaryScreenButton = (Button)findViewById(R.id.button_summary_page);

//        expenseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replaceFragmentContent(new ExpenseFragmnet());
//            }
//        });
//
//        incomeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replaceFragmentContent(new IncomeFragment());
//            }
//        });

        inputScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(new InputScreenFragment());
            }
        });

        summaryScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(new SummaryFragment());
            }
        });
        initFragment();
    }

    private void initFragment(){
        InputScreenFragment inputS = new InputScreenFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fl_main_page_fragment, inputS );

        ft.commit();
    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.fl_main_page_fragment, fragment);

            ft.commit();

        }
    }

}