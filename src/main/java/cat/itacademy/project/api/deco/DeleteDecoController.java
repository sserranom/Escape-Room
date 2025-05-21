package cat.itacademy.project.api.deco;

import cat.itacademy.project.business_logic.deco.application.DeleteDecoService;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteDecoController implements Command<Void> {
    private final DeleteDecoService service;
    private final int idToDelete;

    public DeleteDecoController(int idToDelete) {
        DecoRepository repo = new DecoMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteDecoService(idToDelete, repo);
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}
