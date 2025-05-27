package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.CreateDecoService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateDecoController {
    private final CreateDecoService service;

    public CreateDecoController(CreateDecoDTO createDecoDTO) {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateDecoService(createDecoDTO, repo);
    }

    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}
