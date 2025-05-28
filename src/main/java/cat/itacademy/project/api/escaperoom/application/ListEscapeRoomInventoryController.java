package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.ListEscapeRoomInventoryService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class ListEscapeRoomInventoryController {
    private final ListEscapeRoomInventoryService service;

    public ListEscapeRoomInventoryController() {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new ListEscapeRoomInventoryService(repo);
    }

    public EscapeRoomInventoryDto execute(int escapeRoomId) {
        return service.execute(escapeRoomId);
    }
}
