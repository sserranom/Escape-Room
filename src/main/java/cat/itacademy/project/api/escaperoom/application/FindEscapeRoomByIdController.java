package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindEscapeRoomByIdService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindEscapeRoomByIdController {
    private final FindEscapeRoomByIdService service;

    public FindEscapeRoomByIdController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindEscapeRoomByIdService(repo);
    }

    public Optional<EscapeRoomDTO> execute(int idToFind) {
        return service.execute(idToFind);


    }
}
