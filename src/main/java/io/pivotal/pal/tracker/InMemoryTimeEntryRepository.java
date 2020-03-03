package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final AtomicLong idGenerator= new AtomicLong(0);
    Map <Long,TimeEntry> db= new HashMap<>();

    @Override
    public TimeEntry find(long timeEntryId) {
        return db.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(db.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        if(db.containsKey(eq)){
            any.setId(eq);
            db.put(eq, any);
            return any;
        }
        return null;

    }

    @Override
    public void delete(long timeEntryId) {
        db.remove(timeEntryId);

    }

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        timeEntryToCreate.setId(idGenerator.incrementAndGet());
        db.put(timeEntryToCreate.getId(), timeEntryToCreate);
        return timeEntryToCreate;
    }


}