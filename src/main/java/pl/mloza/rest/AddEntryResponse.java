package pl.mloza.rest;

public class AddEntryResponse {
    private final String message;

    public AddEntryResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
