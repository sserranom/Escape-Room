package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.FindAllDecoService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllDecoController extends MenuCommand<List<DecoDTO>> {
   private final FindAllDecoService service;

    public FindAllDecoController() {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllDecoService(repo);
    }

    @Override
    public Optional<List<DecoDTO>> execute() {
        List<DecoDTO> decoObjs = service.findAll();
        return Optional.of(decoObjs);
    }
}
