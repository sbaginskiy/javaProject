package software.jevera.dao.inmemory;

import org.junit.Test;
import software.jevera.domain.Room;

import static org.junit.Assert.*;

public class RoomInMemoryRepositoryUnitTest {

    private RoomInMemoryRepository roomInMemoryRepository = new RoomInMemoryRepository();

    @Test
    public void save() {

        Room room = new Room("testType");
        assertEquals(room, roomInMemoryRepository.save(room));
    }

    @Test
    public void checkRoom() {

        Room room = new Room("testType");
        Room room2 = new Room("testType2");
        Room room3 = new Room("testType3");

        roomInMemoryRepository.save(room);
        roomInMemoryRepository.save(room2);
        roomInMemoryRepository.save(room3);

        assertEquals(true, roomInMemoryRepository.checkRoom(room));

    }
}