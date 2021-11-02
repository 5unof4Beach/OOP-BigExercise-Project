package com.example.moneymanager.mainprocess;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Input implements Serializable {
    private long amount, type, date, month, year;
    private String note = "", category="";

    public Input(int amount, String note, String category, int type) {
        this.amount = amount;
        this.note = note;
        this.category = category;
        this.type = type;

    }public Input(int amount, String category, int type) {
        this.amount = amount;
        this.category = category;
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getCategory() {
        return category;
    }

    public long getType() {
        return type;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getDate() {
        return date;
    }

    public long getMonth() {
        return month;
    }

    public long getYear() {
        return year;
    }

    @Override
    public String toString(){
        return String.format("%d %s %s", amount,note,category);
    }
}
