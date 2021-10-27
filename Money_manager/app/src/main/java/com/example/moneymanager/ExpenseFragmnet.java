package com.example.moneymanager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ExpenseFragmnet extends Fragment {
    private ListView listView;
    private String items[] = {"Clothes", "Houseware", "Education", "Computer", "Dog"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
//        listView = (ListView) getView().findViewById(R.id.lv_category_list);
//        CategoryAdapter adapter = new CategoryAdapter(this, items);
//        listView.setAdapter(adapter);
//    }
}