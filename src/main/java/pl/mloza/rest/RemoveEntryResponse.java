package pl.mloza.rest;

import pl.mloza.database.Entry;

public class RemoveEntryResponse {
    private final Entry removedEntry;

    public RemoveEntryResponse(Entry removedEntry) {
        this.removedEntry = removedEntry;
    }

    public RemoveEntryResponse() {
        this.removedEntry = null;
    }

    public Entry getRemovedEntry() {
        return removedEntry;
    }
}
