package com.example.moneymanager.repositories;

public class Income {
    private int amount;
    private String note = "", category= "";

    public Income(int amount, String note, String category) {
        this.amount = amount;
        this.note = note;
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getCategory() {
        return category;
    }
}
