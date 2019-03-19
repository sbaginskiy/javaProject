package software.jevera.dao.inmemory;


import software.jevera.dao.RoomRepository;
import software.jevera.domain.Room;

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
