package com.example.moneymanager;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.mainprocess.Input;

import java.util.Vector;

public class ReportFragment extends Fragment {
    InputDataBaseHelper dbHelper;

    private RecyclerView rv_input_Items;
    Vector<Input> list = new Vector<>();
    private int date = 0;
    private int month = 0;
    private int year = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        dbHelper = new InputDataBaseHelper(this.getContext());
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        rv_input_Items = (RecyclerView) getView().findViewById(R.id.rv_report_input_summary);
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        init(datePicker);
        pickDate(datePicker);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void pickDate(DatePicker datePicker) {
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                print("date changed by user");
                date = datePicker.getDayOfMonth();
                month = datePicker.getMonth()+1;
                year = datePicker.getYear();
                list = dbHelper.getAllInput(date, month, year);
                list.sort(((input, t1) -> Long.compare(input.getType(),t1.getType())));
                show(rv_input_Items);
                print(String.format("%d/%d/%d",date,month,year));
            }
        });
    }

    private void show(RecyclerView rv){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(list, this.getContext()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(DatePicker datePicker){
        date = datePicker.getDayOfMonth();
        month = datePicker.getMonth()+1;
        year = datePicker.getYear();
        list = dbHelper.getAllInput(date, month, year);
        list.sort(((input, t1) -> Long.compare(input.getType(),t1.getType())));
        show(rv_input_Items);
    }

    private void print(String s){
        System.out.println(s);
    }

}
