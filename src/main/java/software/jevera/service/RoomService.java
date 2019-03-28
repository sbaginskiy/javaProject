package software.jevera.service;


import org.springframework.stereotype.Service;
import software.jevera.dao.RoomRepository;
import software.jevera.domain.Room;
import software.jevera.exceptions.BussinesException;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom (Room room){
        assertDuplicates(room, "Room with type key " +room.getType() +"already exsist");
        roomRepository.save(room);
        return room;
    }

    private void assertDuplicates(Room room, String message){
        if (isRoomPresent(room)){
            return;
        }
        throw new BussinesException(message);
    }

    private boolean isRoomPresent(Room room) {
        return !roomRepository.checkRoom(room);
    }
}
