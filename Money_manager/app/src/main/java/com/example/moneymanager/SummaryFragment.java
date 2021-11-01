package com.example.moneymanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanager.mainprocess.Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class SummaryFragment extends Fragment {

    String filepath = "InputData";
    File f = new File("/storage/emulated/0/Android/data/InputData/Data.in");
    File f2 = new File("/storage/emulated/0/Android/data/InputData/Data2.in");
    FileInputStream fin;
    FileInputStream fin2;

    {
        try {
            fin = new FileInputStream(f);
            fin2 = new FileInputStream(f2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ;
    ObjectInputStream objectIn;
    ObjectInputStream objectIn2;

    {
        try {
            objectIn = new ObjectInputStream(fin);
            objectIn2 = new ObjectInputStream(fin2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ;

    private RecyclerView rv_expense_Items;
    private RecyclerView rv_income_Items;
    Vector<Input> list = new Vector<>();
    Vector<Input> list2 = new Vector<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Input temp = new Input(0,"",1);
        try {
            list  = (Vector<Input>) objectIn.readObject();
            list2  = (Vector<Input>) objectIn2.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rv_expense_Items = (RecyclerView) getView().findViewById(R.id.rv_expense_summary);
        rv_income_Items = (RecyclerView) getView().findViewById(R.id.rv_income_summary);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
//        showExpenses(rv_expense_Items, layoutManager);
        showIncomes(rv_income_Items, layoutManager);
//        show(rv_expense_Items, rv_income_Items, layoutManager);
    }

    public void showExpenses(RecyclerView rv,LinearLayoutManager layoutManager){
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(list, this.getContext()));
    }

    public void showIncomes(RecyclerView rv,LinearLayoutManager layoutManager){
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        rv.setAdapter(new SummaryAdapter(list2, this.getContext()));
    }

    public void show(RecyclerView rv1,RecyclerView rv2,LinearLayoutManager layoutManager){
        rv1.setLayoutManager(layoutManager);
        rv1.hasFixedSize();
        rv1.setAdapter(new SummaryAdapter(list, this.getContext()));
        rv2.setLayoutManager(layoutManager);
        rv2.hasFixedSize();
        rv2.setAdapter(new SummaryAdapter(list2, this.getContext()));
    }
}
