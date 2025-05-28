package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;

public class DeleteRoomService {
    private final RoomRepository repo;


    public DeleteRoomService(RoomRepository repo) {
        this.repo = repo;
    }


    public void execute(int id) {
        throw new UnsupportedOperationException("not implemented");

    }
}
