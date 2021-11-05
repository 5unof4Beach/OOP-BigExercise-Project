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
import android.widget.Toast;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.repositories.Input;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.Date;
import java.util.Vector;

public class SummaryFragment extends Fragment {
    InputDataBaseHelper dbHelper;

    private Date d = java.util.Calendar.getInstance().getTime();

    PieChart pieChart;
    private RecyclerView rv_input_Items;
    private RecyclerView rv_income_Items;
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
        rv_input_Items = view.findViewById(R.id.rv_expense_summary);
        rv_income_Items = view.findViewById(R.id.rv_income_summary);
        EditText etDate = view.findViewById(R.id.et_summary_date);
        EditText etMonth = view.findViewById(R.id.et_summary_month);
        EditText etYear = view.findViewById(R.id.et_summary_year);
        Button enterButton = view.findViewById(R.id.button_summary_enter);

        pieChart = view.findViewById(R.id.pc_summary);


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

        colors.add(Color.rgb(225,111,84)); // Orange
        colors.add(Color.rgb(26,102,255)); //Blue
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


    private void show(RecyclerView rv, RecyclerView rv2){
        Vector<Input> expenses = new Vector<>();
        Vector<Input> incomes = new Vector<>();

        for(Input i :list){
            if(i.getType() == 1){
                expenses.add(i);
            }
            else {
                incomes.add(i);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(expenses, this.getContext()));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(layoutManager2);
        rv2.hasFixedSize();
        rv2.setAdapter(new SummaryAdapter(incomes, this.getContext()));
    }

    private void enter(Button button,EditText et_date, EditText et_month, EditText et_year){
        button.setOnClickListener(view -> {
            clearAllFocus(et_date, et_month, et_year);
            if(month == 0 || year == 0 || month >12 || year > d.getYear() + 1900){
                Toast.makeText(super.getContext(), "Please enter valid date and month", Toast.LENGTH_SHORT).show();
                System.out.println(month + " " + year);
            }
            else{
                System.out.println(date + " " + month + " " + year);
                list  = dbHelper.getMonthlyInput(date, month, year);
                date = 0;
                month = 0;
                show(rv_input_Items, rv_income_Items);
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
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("")){
                date = Integer.parseInt(temp);
                Log.v("Input","entered");
            }

        });
    }

    private void getMonth(EditText et){
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("") && temp.length()<=2){
                month = Integer.parseInt(temp);
                Log.v("Month",String.format("%d",month));
            }

        });
    }

    private void getYear(EditText et) {
        et.setOnFocusChangeListener((view, b) -> {
            String temp = et.getText().toString();
            if(!temp.equals("") && temp.length()==4){
                year = Integer.parseInt(temp);
                Log.v("Year",String.format("%d",year));
            }

        });
    }
}
