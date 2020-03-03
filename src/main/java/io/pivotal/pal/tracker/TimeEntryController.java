package io.pivotal.pal.tracker;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.pivotal.pal.tracker.TimeEntry;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<?> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        final TimeEntry timeEntry=timeEntryRepository.update(timeEntryId, expected);
        if(Objects.isNull(timeEntry)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping("/time-entries/{nonExistentTimeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long nonExistentTimeEntryId) {
        final TimeEntry timeEntry=timeEntryRepository.find(nonExistentTimeEntryId);
        if(Objects.isNull(timeEntry)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntryToCreate));
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }

}