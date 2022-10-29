package com.example.thibanglai.model;

public class ItemLaw {
    private String namePentally;
    private String moneyPentally;

    public String getNamePentally() {
        return namePentally;
    }

    public void setNamePentally(String namePentally) {
        this.namePentally = namePentally;
    }

    public String getMoneyPentally() {
        return moneyPentally;
    }

    public void setMoneyPentally(String moneyPentally) {
        this.moneyPentally = moneyPentally;
    }

    public ItemLaw(String namePentally, String moneyPentally) {
        this.namePentally = namePentally;
        this.moneyPentally = moneyPentally;
    }
}
