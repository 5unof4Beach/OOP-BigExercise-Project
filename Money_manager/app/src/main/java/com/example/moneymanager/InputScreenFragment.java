package com.example.moneymanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class InputScreenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();
        return inflater.inflate(R.layout.fragment_input_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Button incomeButton = (Button)getView().findViewById(R.id.button_enter_income);
        Button expenseButton = (Button)getView().findViewById(R.id.button_enter_expense);

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragmentContent(new IncomeFragment());
            }
        });

        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(new ExpenseFragmnet());
            }
        });
    }

    private void initFragment(){
        ExpenseFragmnet expenseFragmnet = new ExpenseFragmnet();

        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fl_input_page_fragment, expenseFragmnet );

        ft.commit();
    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getChildFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.fl_input_page_fragment, fragment);

            ft.commit();

        }
    }
}