package com.pluralsight;

import java.time.LocalDateTime;

class Entry {
    private LocalDateTime date;
    private LocalDateTime time;
    private String description;
    private String vendor;
    private int amount;


    public Entry(LocalDateTime date, LocalDateTime time, String description, String vendor, int amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public int getAmount() {
        return amount;
    }


}
