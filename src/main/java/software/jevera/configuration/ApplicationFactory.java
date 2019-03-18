package main.java.software.jevera.configuration;

import main.java.software.jevera.dao.inmemory.EventInMemoryRepository;
import main.java.software.jevera.dao.inmemory.RoomInMemoryRepository;
import main.java.software.jevera.dao.inmemory.UserInMemoryRepository;
import main.java.software.jevera.service.EventService;
import main.java.software.jevera.service.RoomService;
import main.java.software.jevera.service.UserService;


public class ApplicationFactory {

    public static final UserService userService;
    public static final RoomService roomService;
    public static final EventService eventService;

static {
    userService = new UserService(new UserInMemoryRepository());
    EventInMemoryRepository eventRepository = new EventInMemoryRepository();
    roomService = new RoomService(new RoomInMemoryRepository());
    eventService = new EventService(eventRepository);
    }
}
