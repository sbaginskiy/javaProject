package software.jevera.dao.inmemory;


import org.springframework.stereotype.Repository;
import software.jevera.dao.RoomRepository;
import software.jevera.domain.Room;
import software.jevera.exceptions.BussinesException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Repository
public class RoomInMemoryRepository implements RoomRepository {

    private Set<Room> rooms = new HashSet<>();


    @Override
    public Room save(Room room) {
        rooms.add(room);
        return room;
    }

    @Override
    public Boolean checkRoom(Room room) {
        return rooms.contains(room);
    }

    @Override
    public Room getRoomByType(String type) {
        return rooms.stream().filter(room -> room.getType().equals(type)).findAny()
                .orElseThrow(() -> new BussinesException("Room with type "+ type + " not found."));
    }

}
