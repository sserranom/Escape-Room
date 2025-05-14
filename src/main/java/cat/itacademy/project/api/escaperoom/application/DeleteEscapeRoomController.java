package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteEscapeRoomController implements Command<Void> {
    private final DeleteEscapeRoomService service;
    private final int idToDelete;

    public DeleteEscapeRoomController(int idToDelete) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteEscapeRoomService(idToDelete, repo);
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
//        try {
            service.execute();
            return Optional.empty();
//        } catch (Exception e) {
//            error(e.getMessage());
//            return Optional.of(false);
//        }
    }
}
