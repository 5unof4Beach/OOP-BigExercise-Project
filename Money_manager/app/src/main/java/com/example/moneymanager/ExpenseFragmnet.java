package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.mainprocess.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Vector;

public class ExpenseFragmnet extends Fragment {
    Date d = java.util.Calendar.getInstance().getTime();
    InputDataBaseHelper dbHelper;
    Integer amount = 0;
    String category = "";
    String note = "";
    Integer date = d.getDate();
    Integer month = d.getMonth() + 1;
    Integer year = d.getYear() + 1900;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        dbHelper = new InputDataBaseHelper(getContext());
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText amountField = (EditText) view.findViewById(R.id.et_expense_amount);
        EditText noteField = (EditText) view.findViewById(R.id.et_note);
        RadioGroup categoryField = (RadioGroup) view.findViewById(R.id.rg_category);
        Button enterButton = (Button) view.findViewById(R.id.enter_button);

        getAmount(amountField);

        getNote(noteField);


        getCategoryChoice(categoryField);

        enter(enterButton,amountField,noteField);
    }

    private void enter(Button enterButton, EditText amountField, EditText noteField){
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFocus(noteField, amountField);
                if(amount != 0){
                    String show = amount + " " + note + " " + category + " " + date + "/" + month + "/" + year;
                    addToDB();
                    reInit(noteField, amountField);
                    Toast.makeText(ExpenseFragmnet.super.getContext(), show, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(ExpenseFragmnet.super.getContext(), "New Expense Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ExpenseFragmnet.super.getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addToDB(){
        dbHelper.addInput2(new Input(amount, note, category,1),date, month, year);
    }


    private void clearAllFocus(EditText noteField, EditText amountField){
        noteField.clearFocus();
        amountField.clearFocus();
    }

    private void reInit(EditText noteField, EditText amountField){
        amountField.setText("");
        noteField.setText("");
        amount = 0;
        note = "";
    }

    private void getAmount(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    amount = Integer.parseInt(temp);
                }

            }
        });
    }

    private void getDate(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    date = Integer.parseInt(temp);
                }

            }
        });
    }

    private void getMonth(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    month = Integer.parseInt(temp);
                }

            }
        });
    }

    private void getNote(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                note = et.getText().toString();
            }
        });
    }

    private void getCategoryChoice(RadioGroup categoryField){
        int id = categoryField.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) getView().findViewById(id);
        category = button.getText().toString();
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