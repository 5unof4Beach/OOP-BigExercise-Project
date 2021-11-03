package com.example.moneymanager;

import android.graphics.Color;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class SummaryFragment extends Fragment {
    InputDataBaseHelper dbHelper;

    PieChart pieChart;
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
        EditText etYear = (EditText) view.findViewById(R.id.et_summary_year);
        Button enterButton = (Button) view.findViewById(R.id.button_summary_enter);

        pieChart = (PieChart) view.findViewById(R.id.pc_summary);


        getDate(etDate);
        getMonth(etMonth);
        getYear(etYear);
        enter(enterButton, etDate, etMonth,etYear);
    }

    private void addDataToPieChart() {
        Vector<PieEntry> val_entry = new Vector<>();
        Vector<String> name_entry = new Vector<>();
        Vector<Integer> colors = new Vector<>();

        int expense_val = 0;
        int income_val = 0;
        for(Input i:list){
            if(i.getType() == 1){
                expense_val += i.getAmount();
            }
            else {
                income_val += i.getAmount();
            }
        }

        val_entry.add(new PieEntry(expense_val,0));
        val_entry.add(new PieEntry(income_val,1));

        name_entry.add("Expense");
        name_entry.add("Income");

        PieDataSet pieDataSet = new PieDataSet(val_entry,"Expense and Income");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10);

        colors.add(Color.rgb(26,102,255));
        colors.add(Color.rgb(225,111,84));
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


    private void show(RecyclerView rv){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(list, this.getContext()));
    }

    private void enter(Button button,EditText et_date, EditText et_month, EditText et_year){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFocus(et_date, et_month, et_year);
                System.out.println(date + " " + month + " " + year);
                list  = dbHelper.getMonthlyInput(date, month, year);
                date = 0;
                month = 0;
                show(rv_input_Items);
                addDataToPieChart();
            }
        });
    }

    private void clearAllFocus(EditText date, EditText month, EditText year){
        date.clearFocus();
        date.setText("");
        month.clearFocus();
        month.setText("");
        year.clearFocus();
        year.setText("");
    }

    private void getDate(EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    date = Integer.parseInt(temp);
                    Log.v("Input","entered");
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
                    Log.v("Input","entered");
                }

            }
        });
    }

    private void getYear(EditText et) {
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String temp = et.getText().toString();
                if(!temp.equals("")){
                    year = Integer.parseInt(temp);
                    Log.v("Input","entered");
                }

            }
        });
    }
}
