package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindEscapeRoomsService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsController extends MenuCommand<List<EscapeRoomDTO>> {
    private final FindEscapeRoomsService service;


    public FindEscapeRoomsController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindEscapeRoomsService(repo);
    }


    @Override
    public Optional<List<EscapeRoomDTO>> execute() {
        List<EscapeRoomDTO> escapeRooms = service.findAllRaw();

        return Optional.of(escapeRooms);
    }
}
