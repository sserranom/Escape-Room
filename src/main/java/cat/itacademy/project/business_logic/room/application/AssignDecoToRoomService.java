package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomDecoRepository;
import cat.itacademy.project.shared.domain.dtos.room.AssignDecoToRoomDto;

public class AssignDecoToRoomService {
    private final RoomDecoRepository repo;

    public AssignDecoToRoomService(RoomDecoRepository repo) {
        this.repo = repo;
    }

    public void execute(AssignDecoToRoomDto request){
        repo.assignDecoToRoom(request);
    }
}
