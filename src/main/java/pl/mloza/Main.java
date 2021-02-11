package pl.mloza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mloza.database.Entry;
import pl.mloza.rest.AddEntryRequest;
import pl.mloza.rest.AddEntryResponse;
import pl.mloza.rest.ListEntryResponse;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Main {

    private final List<Entry> database = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public Main() {
        database.add(new Entry("one", "one"));
        database.add(new Entry("two", "two"));
        database.add(new Entry("three", "three"));
    }

    @GetMapping("/api/list")
    public ListEntryResponse listObjects() {
        return new ListEntryResponse(database);
    }

    @PostMapping("/api/add")
    public AddEntryResponse addEntry(@RequestBody AddEntryRequest addEntryRequest) {
        database.add(new Entry(addEntryRequest.getKey(), addEntryRequest.getValue()));
        return new AddEntryResponse("Success");
    }
}
