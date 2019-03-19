package software.jevera.service;


import software.jevera.dao.RoomRepository;
import software.jevera.domain.Room;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom (Room room){
        roomRepository.save(room);
        return room;
    }

}
