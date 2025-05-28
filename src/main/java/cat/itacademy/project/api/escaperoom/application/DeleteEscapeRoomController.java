package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.DeleteEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteEscapeRoomController {
    private final DeleteEscapeRoomService service;

    public DeleteEscapeRoomController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteEscapeRoomService(repo);
    }

    public Optional<Void> execute(int idToDelete) {
        service.execute(idToDelete);
        return Optional.empty();
    }
}
