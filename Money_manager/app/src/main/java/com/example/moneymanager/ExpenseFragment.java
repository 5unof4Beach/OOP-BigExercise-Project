package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.repositories.Input;

import java.util.Date;

public class ExpenseFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText amountField = view.findViewById(R.id.et_expense_amount);
        EditText noteField = view.findViewById(R.id.et_note);
        RadioGroup categoryField = view.findViewById(R.id.rg_category);
        Button enterButton = view.findViewById(R.id.enter_button);

        getAmount(amountField);

        getNote(noteField);


        getCategoryChoice(categoryField, view);

        enter(enterButton,amountField,noteField);
    }

    private void enter(Button enterButton, EditText amountField, EditText noteField){
        enterButton.setOnClickListener(view -> {
            clearAllFocus(noteField, amountField);
            if(amount != 0){
                String show = amount + " " + note + " " + category + " " + date + "/" + month + "/" + year;
                addToDB();
                reInit(noteField, amountField);
                Toast.makeText(ExpenseFragment.super.getContext(), show, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(ExpenseFragment.super.getContext(), "New Expense Added", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(ExpenseFragment.super.getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
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
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("")){
                amount = Integer.parseInt(temp);
            }

        });
    }

    private void getDate(EditText et){
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("")){
                date = Integer.parseInt(temp);
            }

        });
    }

    private void getMonth(EditText et){
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("")){
                month = Integer.parseInt(temp);
            }

        });
    }

    private void getNote(EditText et){
        et.setOnFocusChangeListener((view, b) -> note = et.getText().toString());
    }

    private void getCategoryChoice(RadioGroup categoryField, View view){
        int id = categoryField.getCheckedRadioButtonId();
        RadioButton button = view.findViewById(id);
        category = button.getText().toString();
        categoryField.setOnCheckedChangeListener((radioGroup, i) -> {
            int id1 = categoryField.getCheckedRadioButtonId();
            RadioButton button1 = view.findViewById(id1);
            category = button1.getText().toString();
        });
    }

}