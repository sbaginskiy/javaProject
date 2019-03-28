package software.jevera.dao;



import software.jevera.domain.Room;

import java.util.List;

public interface RoomRepository {

    Room save(Room room);

    Boolean checkRoom(Room room);

}
