package main.java.software.jevera.dao.inmemory;

import main.java.software.jevera.dao.RoomRepository;
import main.java.software.jevera.domain.Event;
import main.java.software.jevera.domain.Room;
import main.java.software.jevera.exceptions.BussinesException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomInMemoryRepository implements RoomRepository {

    private Set<Room> rooms = new HashSet<>();


    @Override
    public Room save(Room room) {
        rooms.add(room);
        return room;
    }
}
