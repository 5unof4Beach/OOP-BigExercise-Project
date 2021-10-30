package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.mainprocess.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Vector<Input> expenses = new Vector<>();
    public Vector<Input> inputs = new Vector<>();
    InputScreenFragment inputScreenFragment = new InputScreenFragment();
    SummaryFragment summaryFragment = new SummaryFragment();
    boolean isInInputScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button inputScreenButton = (Button)findViewById(R.id.button_input_page);
        Button summaryScreenButton = (Button)findViewById(R.id.button_summary_page);

        inputScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInInputScreen){
                    replaceFragmentContent(inputScreenFragment);
                    isInInputScreen = true;
                }
            }
        });

        summaryScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInInputScreen){
                    replaceFragmentContent(summaryFragment);
                    isInInputScreen = false;
                }
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