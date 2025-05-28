package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.UpdateDecoService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.deco.UpdateDecoDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateDecoController {
    private final UpdateDecoService service;

    public UpdateDecoController(UpdateDecoDTO updateDecoDTO) {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateDecoService(updateDecoDTO, repo);
    }

    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}
