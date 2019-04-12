package software.jevera.dao.inmemory;

import org.junit.Test;
import software.jevera.domain.Room;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class RoomInMemoryRepositoryIntTest {

    private RoomInMemoryRepository roomInMemoryRepository = new RoomInMemoryRepository();

    @Test
    public void save() {
        Room room = new Room("testType");
        assertEquals(room, roomInMemoryRepository.save(room));
    }

    @Test
    public void checkRoom() {
        List<Room> rooms = asList(new Room("testType"), new Room("testType2"), new Room("testType3"));
        Room room = new Room("testType");
        rooms.forEach(roomInMemoryRepository::save);
        assertTrue(roomInMemoryRepository.checkRoom(room));
    }
}


