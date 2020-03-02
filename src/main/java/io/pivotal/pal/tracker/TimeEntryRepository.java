package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

@Bean
public interface TimeEntryRepository {

    TimeEntry create(TimeEntry t);
    TimeEntry find(long id);
    TimeEntry update(TimeEntry t);
    void delete(long id);
}