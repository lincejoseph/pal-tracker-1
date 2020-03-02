package io.pivotal.pal.tracker;


import java.util.*;
import java.time.LocalDate;

@Service
public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private static Map<Long, TimeEntry> dbMap = new HashMap<Long, TimeEntry>();
    private long idIndex = 1L;
  /*  static {
        TimeEntry t1 = new TimeEntry(1L, 12L, new LocalDate(), 15L);
        dbMap.put(1L,t1);
        TimeEntry t2 = new TimeEntry(2L, 22L, new LocalDate(), 10L);
        dbMap.put(2L,t2);
    } */

    public TimeEntry create(TimeEntry t) {
        dbMap.put(idIndex, t);
        idIndex ++;
        return t;
    }

    public TimeEntry update(TimeEntry t) {
        dbMap.put(t.getId(), t);
        return t;
    }

    public TimeEntry find(long id) {
        return dbMap.get(id);
    }

    public void delete(long id) {
        dbMap.remove(id);
    }







}
