package software.jevera.service;


import org.springframework.stereotype.Service;
import software.jevera.dao.RoomRepository;
import software.jevera.domain.Room;

@Service
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
