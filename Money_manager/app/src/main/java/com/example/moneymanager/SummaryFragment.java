package com.example.moneymanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.mainprocess.Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class SummaryFragment extends Fragment {
    InputDataBaseHelper dbHelper;

    private RecyclerView rv_input_Items;
    Vector<Input> list = new Vector<>();
    private int date = 0;
    private int month = 0;
    private int year = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new InputDataBaseHelper(this.getContext());
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        rv_input_Items = (RecyclerView) getView().findViewById(R.id.rv_expense_summary);
        EditText etDate = (EditText) view.findViewById(R.id.et_summary_date);
        EditText etMonth = (EditText) view.findViewById(R.id.et_summary_month);
        Button enterButton = (Button) view.findViewById(R.id.button_summary_enter);

        getDate(etDate);
        getMonth(etMonth);
        enter(enterButton, etDate, etMonth);
    }

    public void show(RecyclerView rv){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(list, this.getContext()));
    }

    public void enter(Button button,EditText et_date, EditText et_month){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFocus(et_date, et_month);
                list  = dbHelper.getAllInput(date, month, year);
                date = 0;
                month = 0;
                show(rv_input_Items);
            }
        });
    }

    public void clearAllFocus(EditText date, EditText month){
        date.clearFocus();
        date.setText("");
        month.clearFocus();
        month.setText("");
    }

    public void getDate(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    date = Integer.parseInt(temp);
                    Log.v("amount","added");
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
                    Log.v("amount","added");
                }

            }
        });
    }
}
