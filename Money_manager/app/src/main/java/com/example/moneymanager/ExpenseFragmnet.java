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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.moneymanager.mainprocess.Input;

import java.util.Vector;

public class ExpenseFragmnet extends Fragment {
    private ListView listView;
    Vector<Input> expenses = new Vector<>();
    SummaryFragment summaryFragment = new SummaryFragment();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        EditText amountField = (EditText) getView().findViewById(R.id.et_expense_amount);
        EditText noteField = (EditText) getView().findViewById(R.id.et_note);
        RadioGroup categoryField = (RadioGroup) getView().findViewById(R.id.rg_category);
        Button enterButton = (Button) getView().findViewById(R.id.enter_button);

        Integer amount = getAmount(amountField);

        String note = getNote(noteField);

        String category = getCategoryChoice(categoryField);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount == 0){
                    expenses.add(new Input(amount, note, category,1));
                    String show = amount + " " + note + " " + category;
//                    Toast.makeText(ExpenseFragmnet.super.getContext(), "New Expense Added", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ExpenseFragmnet.super.getContext(), show, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ExpenseFragmnet.super.getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int getAmount(EditText amountField){
        Integer amount = 0;
        String temp = amountField.getText().toString();
        if(!temp.equals("")){
            amount = Integer.parseInt(temp);
            Log.v("amount","added");
        }
        return amount;
    }

    public String getNote(EditText noteField){
        return noteField.getText().toString();
    }

    public String getCategoryChoice(RadioGroup categoryField){
        int id = categoryField.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) getView().findViewById(id);
        return button.getText().toString();
    }
}