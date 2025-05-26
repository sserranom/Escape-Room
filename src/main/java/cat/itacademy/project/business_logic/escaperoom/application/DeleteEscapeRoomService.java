package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;

public class DeleteEscapeRoomService {
    private final EscapeRoomRepository repo;

    public DeleteEscapeRoomService( EscapeRoomRepository repo) {
        this.repo = repo;
    }

    public void execute(int idToDelete) {
        repo.delete(idToDelete);
    }
}
