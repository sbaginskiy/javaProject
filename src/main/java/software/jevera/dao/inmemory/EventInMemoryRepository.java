package software.jevera.dao.inmemory;


import org.springframework.stereotype.Repository;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.PeriodicTimeEvent;
import software.jevera.domain.Room;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class EventInMemoryRepository implements EventRrepository {

    private Set<Event> events = new HashSet<>();

    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Event save(Event event) {
        if (event.getId() == null){
            event.setId(counter.incrementAndGet());
        }
        events.add(event);
        return event;
    }

    @Override
    public List<Event> findAllByDateAndRoom(LocalDate date, Room room) {
        List<Event> eventList = new ArrayList<>();
        eventList.addAll(sortPeriodic(date, room));
        eventList.addAll(sortOnce(date, room));
        return eventList;
    }

    @Override
    public List<PeriodicTimeEvent> findAllPeriodic() {
        return events.stream().filter(it -> it instanceof PeriodicTimeEvent).map(PeriodicTimeEvent.class::cast)
                .collect(Collectors.toList());
    }


    public List<PeriodicTimeEvent> sortPeriodic(LocalDate date, Room room) {
        return findAllPeriodic().stream().filter(it -> it.getStartDate().isAfter(date))
                .filter(it -> it.getEndDate().isBefore(date))
                .filter(it -> it.getDay().equals(date.getDayOfWeek().toString()))
                .filter(it -> it.getRoom().equals(room)).collect(Collectors.toList());
    }
    public List<OnceTimeEvent> sortOnce(LocalDate date, Room room) {
        return findAllOnce().stream()
                .filter(it -> it.getDate().equals(date))
                .filter(it -> it.getRoom().equals(room)).collect(Collectors.toList());
    }

    @Override
    public List<OnceTimeEvent> findAllOnce() {
        return  events.stream().filter(it -> it instanceof OnceTimeEvent).map(OnceTimeEvent.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

//    @Override
//    public Event findById(Long id) {
//      List<Event> event = events.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
//      return event.get(0);
//    }

}
