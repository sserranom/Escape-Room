package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.CreateDecoService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.business_logic.room.application.CreateRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateDecoController implements Command<Void> {
    private final CreateDecoService service;

    public CreateDecoController(CreateDecoDTO createDecoDTO) {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateDecoService(createDecoDTO, repo);
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}
