package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class DeleteRoomService extends MenuCommand<Void> {
    private final RoomRepository repo;
    private final int idToDelete;

    public DeleteRoomService(int idToDelete, RoomRepository repo) {
        this.repo = repo;
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
        repo.delete(idToDelete);
        return Optional.empty();
    }
}
