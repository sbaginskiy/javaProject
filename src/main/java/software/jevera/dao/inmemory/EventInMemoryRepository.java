package main.java.software.jevera.dao.inmemory;

import main.java.software.jevera.dao.EventRrepository;
import main.java.software.jevera.domain.Event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


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
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public boolean checkTime(Instant startTime, Instant endTime) {

       List checkList = events.stream().filter(it -> it.getStartTime().isAfter(startTime) && it.getEndTime().isBefore(endTime)).collect(Collectors.toList());



        return !checkList.isEmpty();
    }
}
