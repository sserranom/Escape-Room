package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.application.FindEscapeRoomsService;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsController extends MenuCommand<List<EscapeRoom>> {
    private final FindEscapeRoomsService service;

    public FindEscapeRoomsController(FindEscapeRoomsService service) {
        this.service = service;
    }

    public FindEscapeRoomsController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindEscapeRoomsService(repo);
    }


    @Override
    public Optional<List<EscapeRoom>> execute() {
        List<EscapeRoom> escapeRooms = service.findAllRaw();
        if (escapeRooms.isEmpty()) {
            info("No escape Rooms found.");
        } else {
            info("List of escape Rooms");
            escapeRooms.forEach(room -> log(room.getName() + " (" + room.getUrl()+ ")"));
        }
        return Optional.of(escapeRooms);
    }
}
