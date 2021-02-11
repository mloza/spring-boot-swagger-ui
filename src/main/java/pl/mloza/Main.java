package pl.mloza;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import pl.mloza.database.Entry;
import pl.mloza.rest.AddEntryRequest;
import pl.mloza.rest.AddEntryResponse;
import pl.mloza.rest.ListEntryResponse;
import pl.mloza.rest.RemoveEntryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Operation(
            summary = "List all entries from database",
            description = "Returns all entries that was saved to our database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry was added to database"),
            @ApiResponse(responseCode = "500", description = "Something went wrong, entry was not added")
    })
    @GetMapping("/api/list")
    public ListEntryResponse listObjects() {
        return new ListEntryResponse(database);
    }

    @Operation(
            summary = "Add entry to database",
            description = "Save entry to database. Keys and values can be duplicated."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry was added to database"),
            @ApiResponse(responseCode = "500", description = "Something went wrong, entry was not added")
    })
    @PostMapping("/api/add")
    public AddEntryResponse addEntry(@RequestBody AddEntryRequest addEntryRequest) {
        database.add(new Entry(addEntryRequest.getKey(), addEntryRequest.getValue()));
        return new AddEntryResponse("Success");
    }

    @Operation(
            summary = "Delete entry from database",
            description = "Deletes entry from database. If entries are duplicated, we delete only one instance." +
                    " Returns deleted entry"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry was removed from database"),
            @ApiResponse(responseCode = "500", description = "Something went wrong, entry was not added")
    })
    @DeleteMapping("/api/remove/{key}")
    public RemoveEntryResponse deleteEntry(@PathVariable String key) {
        Optional<Entry> entry = database.stream().filter(e -> e.getKey().equals(key)).findFirst();

        return entry.map(e -> {
            database.remove(e);
            return new RemoveEntryResponse(e);
        }).orElse(new RemoveEntryResponse());
    }
}
