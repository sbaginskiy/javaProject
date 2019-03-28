package software.jevera.dao.inmemory;


import lombok.var;
import org.springframework.stereotype.Repository;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.PeriodicTimeEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<PeriodicTimeEvent> findAllPeriodic() {
        return events.stream().filter(it -> it instanceof PeriodicTimeEvent).map(PeriodicTimeEvent.class::cast)
                .collect(Collectors.toList());
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

    @Override
    public Event findById(Long id) {
      List<Event> event = events.stream().filter(it -> it.getId() == id).collect(Collectors.toList());
      return event.get(0);
    }

}
