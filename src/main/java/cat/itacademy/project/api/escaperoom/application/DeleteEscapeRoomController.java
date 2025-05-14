package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.escaperoom.DeleteEscapeRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteEscapeRoomController extends MenuCommand<Boolean> {
    private final DeleteEscapeRoomService service;
    private final int idToDelete;

    public DeleteEscapeRoomController(int idToDelete) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteEscapeRoomService(idToDelete, repo);
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Boolean> execute() {
        try {
            return service.execute();
        } catch (Exception e) {
            error(e.getMessage());
            return Optional.of(false);
        }
    }
}
