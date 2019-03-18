package main.java.software.jevera.service;

import main.java.software.jevera.dao.RoomRepository;
import main.java.software.jevera.domain.Room;

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
