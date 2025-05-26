package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindAllEscapeRoomsService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllEscapeRoomsController  {
    private final FindAllEscapeRoomsService service;


    public FindAllEscapeRoomsController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllEscapeRoomsService(repo);
    }


    public Optional<List<EscapeRoomDTO>> execute() {
        List<EscapeRoomDTO> escapeRooms = service.findAll();

        return Optional.of(escapeRooms);
    }
}
