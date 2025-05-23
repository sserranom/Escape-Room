package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.FindDecoByIdService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.business_logic.room.application.FindRoomByIdService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindDecoByIdController extends MenuCommand<Optional<DecoDTO>> {
    private final FindDecoByIdService service;
    private final int idToFind;

    public FindDecoByIdController(int idToFind) {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindDecoByIdService(repo, idToFind);
        this.idToFind = idToFind;
    }

    @Override
    public Optional<Optional<DecoDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
            return Optional.empty();
        }
    }
}
