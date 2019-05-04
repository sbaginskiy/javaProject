package software.jevera.dao;


import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.PeriodicTimeEvent;
import software.jevera.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface EventRrepository {

    Event save(Event event);

    List<Event> findAllByDateAndRoom(LocalDate date, Room room);

    List<PeriodicTimeEvent> findAllPeriodic();

    List<OnceTimeEvent> findAllOnce();

    List<Event> findAll();

    Event findById(Long id);

}
