package cat.itacademy.project.business_logic.deco.application;

import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.Optional;

public class FindDecoByIdService implements Command<DecoDTO> {
    private final DecoRepository repo;
    private final int idToFind;

    public FindDecoByIdService(DecoRepository repo, int idToFind) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<DecoDTO> execute() {
        return repo.findById(idToFind);
    }
}
