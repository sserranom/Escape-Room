package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.FindRoomByIdService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindRoomByIdController extends MenuCommand<Optional<RoomDTO>> {
    private final FindRoomByIdService service;
    private final int idToFind;

    public FindRoomByIdController(int idToFind) {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindRoomByIdService(idToFind, repo);
        this.idToFind = idToFind;
    }

    @Override
    public Optional<Optional<RoomDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
            return Optional.empty();
        }
    }
}
