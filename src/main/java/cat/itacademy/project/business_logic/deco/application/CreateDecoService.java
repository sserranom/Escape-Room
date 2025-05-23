package cat.itacademy.project.business_logic.deco.application;

import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateDecoService {
    private final CreateDecoDTO deco;
    private final DecoRepository repo;

    public CreateDecoService(CreateDecoDTO deco, DecoRepository repo) {
        this.deco = deco;
        this.repo = repo;
    }

    public void execute(){
        ensureDoesNotExist();
        repo.create(deco);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<DecoDTO> existing = repo.findByName(deco.name());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Decorative Item " + deco.name() + " already exist");
        }
    }

}
