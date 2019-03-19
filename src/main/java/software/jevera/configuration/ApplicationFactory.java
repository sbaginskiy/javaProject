package software.jevera.configuration;


import software.jevera.dao.inmemory.EventInMemoryRepository;
import software.jevera.dao.inmemory.RoomInMemoryRepository;
import software.jevera.dao.inmemory.UserInMemoryRepository;
import software.jevera.service.EventService;
import software.jevera.service.RoomService;
import software.jevera.service.UserService;

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
