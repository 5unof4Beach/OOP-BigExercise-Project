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
import java.util.Vector;

public class ExpenseFragmnet extends Fragment {
    InputDataBaseHelper dbHelper;
    Vector<Input> expenses = new Vector<>();
    Integer amount = 0;
    Integer date = 1;
    Integer month = 1;
    String category = "";
    String note = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        dbHelper = new InputDataBaseHelper(getContext());
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
//        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText amountField = (EditText) view.findViewById(R.id.et_expense_amount);
        EditText noteField = (EditText) view.findViewById(R.id.et_note);
        EditText dateField = (EditText) view.findViewById(R.id.et_day);
        EditText monthField = (EditText) view.findViewById(R.id.et_month);
        RadioGroup categoryField = (RadioGroup) view.findViewById(R.id.rg_category);
        Button enterButton = (Button) view.findViewById(R.id.enter_button);

        getAmount(amountField);

        getNote(noteField);

        getDate(dateField);

        getMonth(monthField);

        getCategoryChoice(categoryField);

        enter(enterButton,amountField,noteField,dateField,monthField);
    }

    public void enter(Button enterButton, EditText amountField, EditText noteField, EditText dateField, EditText monthField){
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFocus(noteField, amountField,dateField,monthField);
                if(amount != 0){
                    String show = amount + " " + note + " " + category + " " + date + "/" + month;
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

    public void addToList(){
        expenses.add(new Input(amount, note, category,1));
    }

    public void addToDB(){
//        dbHelper.addInput(new Input(amount, note, category,1));
        dbHelper.addInput2(new Input(amount, note, category,1),date, month);
    }


    public void clearAllFocus(EditText noteField, EditText amountField, EditText dateField, EditText monthField){
        noteField.clearFocus();
        amountField.clearFocus();
        dateField.clearFocus();
        monthField.clearFocus();
    }

    public void reInit(EditText noteField, EditText amountField){
        amountField.setText("");
        noteField.setText("");
        amount = 0;
        note = "";
    }

    public void getAmount(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    amount = Integer.parseInt(temp);
                    Log.v("amount","added");
                }

            }
        });
    }

    public void getDate(EditText et){
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

    public void getMonth(EditText et){
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

    public void getNote(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                note = et.getText().toString();
            }
        });
    }

    public void getCategoryChoice(RadioGroup categoryField){
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