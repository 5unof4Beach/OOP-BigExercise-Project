package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.fragment.app.Fragment;

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

import com.example.moneymanager.mainprocess.Input;

import java.util.Vector;

public class ExpenseFragmnet extends Fragment {
    private ListView listView;
    Vector<Input> expenses = new Vector<>();
    Integer amount = 0;
    String category = "";
    String note = "";

    SummaryFragment summaryFragment = new SummaryFragment();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
//        initView(view);
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

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount != 0){
                    clearAllFocus(noteField, amountField);
                    String show = amount + " " + note + " " + category;
                    addToList();
                    reInit(noteField, amountField);
//                    Toast.makeText(ExpenseFragmnet.super.getContext(), "New Expense Added", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ExpenseFragmnet.super.getContext(), show, Toast.LENGTH_SHORT).show();
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

    public void clearAllFocus(EditText noteField, EditText amountField){
        noteField.clearFocus();
        amountField.clearFocus();
    }

    public void reInit(EditText noteField, EditText amountField){
        amountField.setText("");
        noteField.setText("");
        amount = 0;
        note = "";
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

    public void getNote(EditText noteField){
        noteField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                note = noteField.getText().toString();
            }
        });
    }

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