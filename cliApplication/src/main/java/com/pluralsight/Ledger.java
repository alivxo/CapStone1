package com.pluralsight;

import java.util.ArrayList;

class Ledger {
    private ArrayList<Entry> entries;

    public Ledger() {
        entries = new ArrayList<>();

    }

    public void addEntry(Entry entry) {
        entries.add(entry);
//    }
//    public void addEntries (Entry reportEntry) {
//        entries.add(reportEntry);
    }

    public void displayAllEntries() {
        for (Entry entry : entries) {
            System.out.println(entry.getDescription() + ": $ " + entry.getAmount());
        }
    }

}