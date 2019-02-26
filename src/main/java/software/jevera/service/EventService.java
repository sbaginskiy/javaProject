package main.java.software.jevera.service;

import main.java.software.jevera.dao.EventRrepository;
import main.java.software.jevera.domain.Event;
import main.java.software.jevera.domain.User;
import main.java.software.jevera.exceptions.BussinesException;

import java.util.List;

public class EventService {

    private final EventRrepository eventRrepository;


    public EventService(EventRrepository eventRrepository) {
        this.eventRrepository = eventRrepository;
    }

    public Event createEvent(Event event, User user){
        if (eventRrepository.checkTime(event.getStartTime(), event.getEndTime())) {
            event.setUser(user);
            return eventRrepository.save(event);
        }else throw new BussinesException("Time is already reserved!");
    }
    public List<Event> getAllEvents() {
        return this.eventRrepository.findAll();
    }
}
