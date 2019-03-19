package software.jevera.dao;


import software.jevera.domain.Event;

import java.time.Instant;
import java.util.List;

public interface EventRrepository {

    Event save(Event event);

    List<Event> findAll();


}
