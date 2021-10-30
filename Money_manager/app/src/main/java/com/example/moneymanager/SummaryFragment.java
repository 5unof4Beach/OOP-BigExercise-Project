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
    FileInputStream fin;

    {
        try {
            fin = new FileInputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ;
    ObjectInputStream objectIn;

    {
        try {
            objectIn = new ObjectInputStream(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ;

    private RecyclerView rvItems;
    Vector<Input> list = new Vector<>();
//    Vector<IncomeFragment> incomes = new Vector<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        list.add(temp);
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }
        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Input temp = new Input(0,"",1);
        try {
            list  = (Vector<Input>) objectIn.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rvItems = (RecyclerView) getView().findViewById(R.id.rv_summary);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rvItems.setLayoutManager(layoutManager);
        rvItems.hasFixedSize();
        rvItems.setAdapter(new SummaryAdapter(list, this.getContext()));
    }
}
//        list.add(new Input(5000,"tien di net","computer",1));
//        list.add(new Input(100000,"tien dong hoc","study",2));
//        list.add(new Input(22000,"","houseware",1));
//        list.add(new Input(500000,"tien da pho","entertainment",2));
