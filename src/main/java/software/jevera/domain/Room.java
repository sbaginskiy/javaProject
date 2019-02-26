package main.java.software.jevera.domain;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private List<Event> events = new ArrayList();

    private RoomKey key;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public RoomKey getKey() {
        return key;
    }

    public void setKey(RoomKey key) {
        this.key = key;
    }
}
