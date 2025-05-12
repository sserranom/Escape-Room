package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.application.FindEscapeRooms;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsController extends MenuCommand<List<EscapeRoomDTO>> {
    private final FindEscapeRooms service;

    public FindEscapeRoomsController(FindEscapeRooms service) {
        this.service = service;
    }

    public FindEscapeRoomsController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindEscapeRooms(repo);
    }


    @Override
    public Optional<List<EscapeRoomDTO>> execute() {
        List<EscapeRoomDTO> escapeRooms = service.findAll();
        if (escapeRooms.isEmpty()) {
            info("No escape Rooms found.");
        } else {
            info("List of escape Rooms");
            escapeRooms.forEach(room -> log(room.name() + " (" + room.url() + ")"));
        }
        return Optional.of(escapeRooms);
    }
}
