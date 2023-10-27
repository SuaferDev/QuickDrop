package com.example.quickdrop;

public class PayType {
    private final long cardName;
    private final String date;
    private final int cvs;

    public PayType(){
        this.cardName = 0;
        this.date = "name";
        this.cvs = 0;
    }

    public PayType(long cardName, String date, short cvs){
        this.cardName = cardName;
        this.date = date;
        this.cvs = cvs;
    }

    public long getCardName() {return cardName;}
    public String formatNumber(long number) {
        if (number < 0 || number > 9999999999999999L) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 0 до 9999999999999999");
        }

        return String.format("%04d %04d %04d %04d",
                (number / 1000000000000L) % 10000,
                (number / 100000000L) % 10000,
                (number / 10000L) % 10000,
                number % 10000);
    }
    public String getDate() {return date;}
    public int getCvs() {return cvs;}
}
