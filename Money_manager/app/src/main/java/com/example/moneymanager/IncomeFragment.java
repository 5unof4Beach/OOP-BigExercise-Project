package com.example.moneymanager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.moneymanager.mainprocess.Input;

import java.util.Vector;

public class IncomeFragment extends Fragment {
    Vector<Input> expenses = new Vector<>();
    Integer amount = 0;
    String category = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText amountField = (EditText) view.findViewById(R.id.et_income_amount);
        RadioGroup categoryField = (RadioGroup) view.findViewById(R.id.rg_category);
        Button enterButton = (Button) view.findViewById(R.id.enter_button);

        getAmount(amountField);

        getCategoryChoice(categoryField);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFocus(amountField);
                if(amount != 0){
                    String show = amount + " " + category;
                    addToList();
                    reInit(amountField);
//                    Toast.makeText(ExpenseFragmnet.super.getContext(), "New Expense Added", Toast.LENGTH_SHORT).show();
                    Toast.makeText(IncomeFragment.super.getContext(), show, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(IncomeFragment.super.getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addToList(){
        expenses.add(new Input(amount, category,2));
    }

    public void clearAllFocus(EditText amountField){
        amountField.clearFocus();
    }

    public void reInit(EditText amountField){
        amountField.setText("");
        amount = 0;
    }
    public void getAmount(EditText amountField){
        amountField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = amountField.getText().toString();
                if(!temp.equals("")){
                    amount = Integer.parseInt(temp);
                    Log.v("amount","added");
                }

            }
        });
    }

//    public void getNote(EditText noteField){
//        noteField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                note = noteField.getText().toString();
//            }
//        });
//    }

    public void getCategoryChoice(RadioGroup categoryField){
        categoryField.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = categoryField.getCheckedRadioButtonId();
                RadioButton button = (RadioButton) getView().findViewById(id);
                category = button.getText().toString();
            }
        });
    }
}