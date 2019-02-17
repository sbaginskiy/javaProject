package main.java.software.jevera.service;

import main.java.software.jevera.dao.EventRrepository;
import main.java.software.jevera.domain.Event;
import main.java.software.jevera.domain.User;

public class EventService {

    private final EventRrepository eventRrepository;


    public EventService(EventRrepository eventRrepository) {
        this.eventRrepository = eventRrepository;
    }

    public Event createEvent(Event event, User user){
        event.setUser(user);
        return eventRrepository.save(event);
    }

}
