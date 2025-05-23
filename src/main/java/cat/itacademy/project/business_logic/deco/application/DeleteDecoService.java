package cat.itacademy.project.business_logic.deco.application;

import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class DeleteDecoService extends MenuCommand<Void> {
    private final DecoRepository repo;
    private final int idToDelete;

    public DeleteDecoService(int idToDelete, DecoRepository repo) {
        this.repo = repo;
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
        repo.delete(idToDelete);
        return Optional.empty();
    }
}
