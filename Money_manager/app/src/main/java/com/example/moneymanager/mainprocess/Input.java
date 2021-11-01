package com.example.moneymanager.mainprocess;

import java.io.Serializable;

public class Input implements Serializable {
    private long amount,type;
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
}
