package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class DeleteEscapeRoomService extends MenuCommand<Boolean> {
    private final EscapeRoomRepository repo;
    private final int idToDelete;

    public DeleteEscapeRoomService(int idToDelete, EscapeRoomRepository repo)  {
        this.repo = repo;
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Boolean> execute() {
        Optional<Void> result = repo.delete(idToDelete);
        return Optional.of(!result.isEmpty());
    }
}
