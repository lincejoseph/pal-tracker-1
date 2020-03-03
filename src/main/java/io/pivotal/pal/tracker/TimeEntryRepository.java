package io.pivotal.pal.tracker;

import java.util.Collection;
import java.util.List;

import io.pivotal.pal.tracker.TimeEntry;

public interface TimeEntryRepository {

    TimeEntry find(long timeEntryId);

    List<TimeEntry> list();

    TimeEntry update(long eq, TimeEntry any);

    void delete(long timeEntryId);

    TimeEntry create(TimeEntry timeEntryToCreate);

}