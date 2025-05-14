package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindEscapeRoomByIdService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindEscapeRoomByIdController extends MenuCommand<Optional<EscapeRoomDTO>> {
    private final FindEscapeRoomByIdService service;
    private final int idToFind;

    public FindEscapeRoomByIdController(int idToFind) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindEscapeRoomByIdService(idToFind, repo);
        this.idToFind = idToFind;
    }

    @Override
    public Optional<Optional<EscapeRoomDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
            return Optional.empty();
        }
    }
}
