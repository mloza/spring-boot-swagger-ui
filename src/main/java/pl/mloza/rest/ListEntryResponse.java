package pl.mloza.rest;

import pl.mloza.database.Entry;

import java.util.List;

public class ListEntryResponse {
    private List<Entry> entries;

    public ListEntryResponse(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
