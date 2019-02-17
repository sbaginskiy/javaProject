package main.java.software.jevera.dao;

import main.java.software.jevera.domain.Event;

import java.util.List;

public interface EventRrepository {

    Event save(Event event);

    List<Event> findAll();

}
