package software.jevera.dao;


import software.jevera.domain.Event;
import software.jevera.domain.PeriodicTimeEvent;

import java.time.Instant;
import java.util.List;

public interface EventRrepository {

    Event save(Event event);

    List<Event> findAllPeriodic();

    List<Event> findAllOnce();

    List<Event> findAll();


    Event findById(Long id);

}
